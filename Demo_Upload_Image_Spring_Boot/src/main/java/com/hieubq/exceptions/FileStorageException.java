/**
 * 2023
 * FileStorageException.java, BUI QUANG HIEU
 */
package com.hieubq.exceptions;

/**
 * @author BUI_QUANG_HIEU
 */
public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
