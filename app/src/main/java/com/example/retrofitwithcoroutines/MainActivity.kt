package com.example.retrofitwithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitwithcoroutines.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
//        getRequestWithPathParameters()
//        getRequestWithQueryParameters()
        uploadingAlbum()
    }

    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)//getAlbums()
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val text = "  " + "Album Title : ${albumsItem.title} \n"+
                            "  " + "Album Id : ${albumsItem.id} \n"+
                            "  " + "Album UserId : ${albumsItem.userId} \n\n\n"
                    Log.i("TAG :: ", "$text")
                    binding.tvText.append(text)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAblum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(this, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadingAlbum() {
        val album = AlbumsItem(999, "My SONG" , 9)
        val postResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, Observer {
            val receivedAlbumsItem: AlbumsItem? = it.body()
            val text = "  " + "Album Title : ${receivedAlbumsItem!!.title} \n"+
                    "  " + "Album Id : ${receivedAlbumsItem!!.id} \n"+
                    "  " + "Album UserId : ${receivedAlbumsItem!!.userId} \n\n\n"
            Log.i("TAG :: ", "$text")
            binding.tvText.append(text)
        })
    }
}