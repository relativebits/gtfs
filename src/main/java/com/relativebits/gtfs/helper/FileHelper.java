package com.relativebits.gtfs.helper;

import com.relativebits.gtfs.common.Logger;
import org.apache.commons.io.FileUtils;

import java.io.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class FileHelper {

    public static boolean createFolder(String fullFolderPath) {
        try {
            File folder = new File(fullFolderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            folder.setExecutable(true);
            folder.setWritable(true);
            return doesFileOrFolderExist(fullFolderPath);
        } catch (Exception ex) {
            Logger.error(FileHelper.class, ex);
            return false;
        }
    }

    public static boolean deleteFolderOrFile(String fullPath) {
        try {
            File file = new File(fullPath);
            if (file.isDirectory()) {
                FileUtils.deleteDirectory(file);
            } else {
                FileUtils.deleteQuietly(file);
            }
            return true;
        } catch (Exception ex) {
            Logger.error(FileHelper.class, ex);
            return false;
        }
    }

    public static boolean doesFileOrFolderExist(String fullPath) {
        try {
            return new File(fullPath).exists();
        } catch (Exception ex) {
            Logger.error(FileHelper.class, ex);
            return false;
        }
    }

    public static boolean downloadFile(String fileUrl, String fileLocation) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(fileUrl);

        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                File of = new File(fileLocation);
                try (InputStream instream = entity.getContent(); FileOutputStream fos = new FileOutputStream(of)) {
                    for (long i = 0; i < entity.getContentLength(); i++) {
                        fos.write(instream.read());
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.error(FileHelper.class, ex);
            return false;
        }
    }

    public static boolean unpackFile(String packedFilePath, String unpackedFilePath) {
        FileInputStream fileInputStream;
        byte[] buffer = new byte[1024];

        try {
            fileInputStream = new FileInputStream(packedFilePath);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(unpackedFilePath + File.separator + fileName);

                Logger.info(FileHelper.class, "Unpacking to " + newFile.getAbsolutePath());

                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            IOUtils.closeQuietly(zipInputStream);
            IOUtils.closeQuietly(fileInputStream);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
