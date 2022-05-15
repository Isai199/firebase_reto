package com.firebase_reto

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase_reto.databinding.ShowItemBinding
import org.json.JSONObject

class MovieAdapter( val context: Context, private val movies: Array<JSONObject>): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MovieHolder(context,binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieHolder, position: Int) {
        holder.render(movies[position])
    }

    override fun getItemCount(): Int = movies.size


    class MovieHolder(val context: Context,val binding: ShowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun render(movie: JSONObject){

            binding.idTVTitle.setText("Title:"+movie.getString("title"))
            binding.idTVYear.setText("Year:"+movie.getString("year"))
            binding.idTVType.setText("Type:"+movie.getString("type"))
            binding.idTVCountry.setText("Country:"+movie.getString("country"))
            binding.idTVGender.setText("Gender:"+movie.getString("gender"))
            /*binding.tvChannelName.setText(video.getString("title"))
            binding.tvVideoViews.setText(video.getString("title"))
            binding.tvdateposted.setText(video.getString("title"))
            binding.tvVideoDuration.setText(video.getString("title"))
            binding.ivVideoThumbnail.setImageResource(R.drawable.video_placeholder)*/

            binding.idIVButtonToLink.setOnClickListener {

                val url = movie.getString("url")
                val nombre = movie.getString("title")

                Toast.makeText(context, "$url"+" para "+ "$nombre", Toast.LENGTH_LONG).show()



                //codigo para redireccionar a pagina con el cartel de la pelicula

                /*val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context?.startActivity(browserIntent)*/
                /*val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)*/
                /*val webpage: Uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(intent)*/
                /*val openURL = Intent(android.content.Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://www.tutorialkart.com/")
                startActivity(openURL)*/

            }

        }


    }


}

