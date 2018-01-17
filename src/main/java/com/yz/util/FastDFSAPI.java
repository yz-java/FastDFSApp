package com.yz.util;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSAPI {

    private static StorageClient storageClient = null;

	static{
		String resourcePath = FastDFSAPI.class.getResource("/").getPath();
		try {
			ClientGlobal.init(resourcePath+"fdfs_client.conf");
            // 建立连接
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = tracker.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 上传文件
     * @param fileByte 文件字节
     * @param file_ext_name 文件名
     * @param metaList 文件元数据
     * @return
     */
    public static String[] uploadFile(byte[]fileByte, String file_ext_name, Map<String,String> metaList) {
        try {
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            return storageClient.upload_file(fileByte, file_ext_name,nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件元数据
     * @param fileId 文件ID
     * @return
     */
    public static Map<String,String> getFileMetadata(String fileId) {
        int indexOf = fileId.indexOf("/");
        String group_name = fileId.substring(0,indexOf);
        String remote_filename = fileId.substring(indexOf+1);
        try {
            NameValuePair[] metaList = storageClient.get_metadata(group_name,remote_filename);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public static int deleteFile(String fileId) {
        int indexOf = fileId.indexOf("/");
        String group_name = fileId.substring(0,indexOf);
        String remote_filename = fileId.substring(indexOf+1);
        try {
            return storageClient.delete_file(group_name,remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 下载文件
     * @param fileId 文件ID（上传文件成功后返回的ID）
     * @param filePath 文件下载保存位置
     * @return
     */
    public static File downloadFile(String fileId,String filePath) {
        File outFile = new File(filePath);
        int indexOf = fileId.indexOf("/");
        String group_name = fileId.substring(0,indexOf);
        String remote_filename = fileId.substring(indexOf+1);
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient.download_file(group_name,remote_filename);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
            fos = new FileOutputStream(outFile);
            IOUtils.copy(inputStream,fos);
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
