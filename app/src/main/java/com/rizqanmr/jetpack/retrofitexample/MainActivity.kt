package com.rizqanmr.jetpack.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.rizqanmr.jetpack.retrofitexample.data.entity.AlbumEntity
import com.rizqanmr.jetpack.retrofitexample.data.entity.AlbumItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService : ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retService = RetrofitInstance.getRetrofitInstance()
            .create(ApiService::class.java)
        getRequestWithQueryParameters()
        //getRequestWithPathParameters()
        //uploadAlbum()

    }

    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<AlbumEntity>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " "+"Album Title : ${albumsItem.title}"+"\n"+
                            " "+"Album id : ${albumsItem.id}"+"\n"+
                            " "+"User id : ${albumsItem.userId}"+"\n\n\n"
                    text_view.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum() {
        val album = AlbumItem(0, "My Title", 3)
        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " "+"Album Title : ${receivedAlbumsItem?.title}"+"\n"+
                    " "+"Album id : ${receivedAlbumsItem?.id}"+"\n"+
                    " "+"User id : ${receivedAlbumsItem?.userId}"+"\n\n\n"
            text_view.text = result
        })
    }
}