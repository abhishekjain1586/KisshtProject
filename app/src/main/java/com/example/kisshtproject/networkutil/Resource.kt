package com.example.kisshtproject.networkutil

data class Resource<out T>(val status: Status, val data: T?, val errorMsg: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> failure(msg: String?): Resource<T> = Resource(Status.FAILURE, null, msg)
    }
}
