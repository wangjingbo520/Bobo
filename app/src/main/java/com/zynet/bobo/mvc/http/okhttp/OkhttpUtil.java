package com.zynet.bobo.mvc.http.okhttp;

import android.content.Context;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Bobo
 * @date 2019/10/19 0019
 * describe
 */
public class OkhttpUtil {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";

    public static final String FILE_TYPE_FILE = "file/*";
    public static final String FILE_TYPE_IMAGE = "image/*";
    public static final String FILE_TYPE_AUDIO = "audio/*";
    public static final String FILE_TYPE_VIDEO = "video/*";


    /**
     * get请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(Context context,String url, CallBackUtil callBack, boolean isShowDialog) {
        okHttpGet(context,url, null, null, callBack, isShowDialog);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(Context context,String url, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpGet(context,url, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(Context context,String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_GET, url, paramsMap, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(Context context,String url, CallBackUtil callBack, boolean isShowDialog) {
        okHttpPost(context,url, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(Context context,String url, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpPost(context,url, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(Context context,String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_POST, url, paramsMap, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(Context context,String url, CallBackUtil callBack, boolean isShowDialog) {
        okHttpPut(context,url, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(Context context,String url, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpPut(context,url, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(Context context,String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_PUT, url, paramsMap, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(Context context,String url, CallBackUtil callBack, boolean isShowDialog) {
        okHttpDelete(context,url, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(Context context,String url, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpDelete(context,url, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(Context context,String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_DELETE, url, paramsMap, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(Context context,String url, String jsonStr, CallBackUtil callBack, boolean isShowDialog) {
        okHttpPostJson(context,url, jsonStr, null, callBack, isShowDialog);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(Context context,String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_POST, url, jsonStr, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(Context context,String url, File file, String fileKey, String fileType, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadFile(context,url, file, fileKey, fileType, null, callBack, isShowDialog);
    }

    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(Context context,String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadFile(context,url, file, fileKey, fileType, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(Context context,String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_POST, url, paramsMap, file, fileKey, fileType, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(Context context,String url, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadListFile(context,url, null, fileList, fileKey, fileType, callBack, isShowDialog);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(Context context,String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadListFile(context,url, paramsMap, fileList, fileKey, fileType, null, callBack, isShowDialog);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(Context context,String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_POST, url, paramsMap, fileList, fileKey, fileType, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(Context context,String url, Map<String, File> fileMap, String fileType, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadMapFile(context,url, fileMap, fileType, null, callBack, isShowDialog);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(Context context,String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, CallBackUtil callBack, boolean isShowDialog) {
        okHttpUploadMapFile(context,url, fileMap, fileType, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(Context context,String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack, boolean isShowDialog) {
        new RequestUtil(context,METHOD_POST, url, paramsMap, fileMap, fileType, headerMap, callBack, isShowDialog).execute();
    }

    /**
     * 下载文件,不带参数
     */
    public static void okHttpDownloadFile(Context context,String url, CallBackUtil.CallBackFile callBack, boolean isShowDialog) {
        okHttpDownloadFile(context,url, null, callBack, isShowDialog);
    }

    /**
     * 下载文件,带参数
     */
    public static void okHttpDownloadFile(Context context,String url, Map<String, String> paramsMap, CallBackUtil.CallBackFile callBack, boolean isShowDialog) {
        okHttpGet(context,url, paramsMap, null, callBack, isShowDialog);
    }

    /**
     * 加载图片
     */
    public static void okHttpGetBitmap(Context context,String url, CallBackUtil.CallBackBitmap callBack, boolean isShowDialog) {
        okHttpGetBitmap(context,url, null, callBack, isShowDialog);
    }

    /**
     * 加载图片，带参数
     */
    public static void okHttpGetBitmap(Context context,String url, Map<String, String> paramsMap, CallBackUtil.CallBackBitmap callBack, boolean isShowDialog) {
        okHttpGet(context,url, paramsMap, null, callBack, isShowDialog);
    }


}
