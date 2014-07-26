package com.urucas.sting.callback;

import com.urucas.sting.model.CustomError;

public interface LoginCallback {

	public void onSuccess();
	public void onError(CustomError error);
}
