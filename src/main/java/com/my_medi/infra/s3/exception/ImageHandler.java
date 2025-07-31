package com.my_medi.infra.s3.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ImageHandler extends GeneralException {

    public static final GeneralException REQUEST_EMPTY =
            new ImageHandler(ImageErrorStatus.IMAGE_REQUEST_IS_EMPTY);

    public static final GeneralException WRONG_FORM =
            new ImageHandler(ImageErrorStatus.IMAGE_FORM_IS_WRONG);

    public static final GeneralException UPLOAD_FAIL =
            new ImageHandler(ImageErrorStatus.IMAGE_FILE_UPLOAD_FAIL);

    public ImageHandler(BaseErrorCode code) {
        super(code);
    }
}
