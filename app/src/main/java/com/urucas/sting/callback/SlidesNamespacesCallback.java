package com.urucas.sting.callback;

import com.urucas.sting.model.CustomError;
import com.urucas.sting.model.SlideNamespace;

import java.util.ArrayList;

public interface SlidesNamespacesCallback {

	public void onSuccess(ArrayList<SlideNamespace> namespaces);
	public void onError(CustomError error);
}
