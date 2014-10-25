/*
*  ImageProcessing.cpp
* package com.cabatuan.cannynative;
*/
#include <jni.h>

#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace std;
using namespace cv;

Mat * mCanny = NULL;

extern "C"
jboolean
Java_com_cabatuan_cannynative_CameraPreview_ImageProcessing(
		JNIEnv* env, jobject thiz,
		jint width, jint height,
		jbyteArray NV21FrameData, jintArray outPixels)
{
	jbyte * pNV21FrameData = env->GetByteArrayElements(NV21FrameData, 0);
	jint * poutPixels = env->GetIntArrayElements(outPixels, 0);

	if ( mCanny == NULL )
    	{
    		mCanny = new Mat(height, width, CV_8UC1);
    	}

	Mat mGray(height, width, CV_8UC1, (unsigned char *)pNV21FrameData);
	Mat mResult(height, width, CV_8UC4, (unsigned char *)poutPixels);
	Mat CannyImg = *mCanny;

	Canny(mGray, CannyImg, 65, 100);
	cvtColor(CannyImg, mResult, CV_GRAY2BGRA);

	env->ReleaseByteArrayElements(NV21FrameData, pNV21FrameData, 0);
	env->ReleaseIntArrayElements(outPixels, poutPixels, 0);
	return true;
}

// void Canny(InputArray image, OutputArray edges, double threshold1, double threshold2, int apertureSize=
//   3, bool L2gradient=false )