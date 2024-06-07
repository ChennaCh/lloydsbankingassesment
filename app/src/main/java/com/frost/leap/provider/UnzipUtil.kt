package com.frost.leap.provider

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipInputStream


/**
 * Created by Chenna Rao on 06/06/2023.
 * <p>
 * Frost Interactive
 */
object UnzipUtil {
    /**
     * Size of the buffer to read/write data
     */
    private const val BUFFER_SIZE = 4096

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    @Throws(IOException::class)
    fun unzip(zipFilePath: String?, destDirectory: String) {
        val destDir = File(destDirectory)
        if (!destDir.exists()) {
            destDir.mkdir()
        }
        val zipIn = ZipInputStream(FileInputStream(zipFilePath))
        var entry = zipIn.nextEntry
        // iterates over entries in the zip file
        while (entry != null) {
            val filePath = destDirectory + File.separator + entry.name
            if (!entry.isDirectory) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath)
            } else {
                // if the entry is a directory, make the directory
                val dir = File(filePath)
                dir.mkdir()
            }
            zipIn.closeEntry()
            entry = zipIn.nextEntry
        }
        zipIn.close()
    }

    /**
     * Extracts a file from a zip to specified destination directory.
     * The path of the file inside the zip is discarded, the file is
     * copied directly to the destDirectory.
     *
     * @param zipFilePath   - path and file name of a zip file
     * @param inZipFilePath - path and file name inside the zip
     * @param destDirectory - directory to which the file from zip should be extracted, the path part is discarded.
     * @throws IOException
     */
    @Throws(IOException::class)
    fun extractFile(zipFilePath: String?, inZipFilePath: String, destDirectory: String) {
        val zipIn = ZipInputStream(FileInputStream(zipFilePath))
        var entry = zipIn.nextEntry
        // iterates over entries in the zip file
        while (entry != null) {
            if (!entry.isDirectory && inZipFilePath == entry.name) {
                var filePath = entry.name
                val separatorIndex = filePath.lastIndexOf(File.separator)
                if (separatorIndex > -1) filePath =
                    filePath.substring(separatorIndex + 1, filePath.length)
                filePath = destDirectory + File.separator + filePath
                extractFile(zipIn, filePath)
                break
            }
            zipIn.closeEntry()
            entry = zipIn.nextEntry
        }
        zipIn.close()
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun extractFile(zipIn: ZipInputStream, filePath: String) {
        val bos = BufferedOutputStream(FileOutputStream(filePath))
        val bytesIn = ByteArray(BUFFER_SIZE)
        var read = 0
        while (zipIn.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()
    }
}
