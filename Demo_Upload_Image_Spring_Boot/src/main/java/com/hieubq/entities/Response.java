/**
 * 2023
 * Response.java, BUI QUANG HIEU
 */
package com.hieubq.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author BUI_QUANG_HIEU
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
}
