package org.jbulletin.form;

import org.springframework.web.multipart.MultipartFile;

public class AvatarUploadForm {
    private MultipartFile file;

    public MultipartFile getFile() {
	return file;
    }

    public void setFile(MultipartFile file) {
	this.file = file;
    }

}
