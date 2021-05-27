package com.example.parcialfinal

import android.graphics.Bitmap
import android.net.Uri
import com.example.parcialfinal.databinding.ItemNameBinding
import com.google.android.gms.maps.model.LatLng
import com.xwray.groupie.databinding.BindableItem

class NameItem(var name: String, val code: String, val location: String,
               val backgroundImage: Int?, var image: String, var uriImage: Uri?,
               var bitmapImagen: Bitmap?, val fragment: GroupFragment?, val requestCode: Int,
               val locationLatlng: LatLng): BindableItem<ItemNameBinding>() {

    override fun bind(viewBinding: ItemNameBinding, position: Int) {
        viewBinding.nameTextView.text = name
        viewBinding.codeTextView.text = code
        viewBinding.locationTextView.text = location
        viewBinding.locationLatlngTextView.text = locationLatlng.toString()
        if(image.equals("int")){
            backgroundImage?.let { viewBinding.studentImagenButton.setImageResource(it) }
            viewBinding.studentImagenButton.setImageURI(uriImage)
        } else{
            viewBinding.studentImagenButton.setImageBitmap(bitmapImagen)
        }

        viewBinding.studentImagenButton.setOnClickListener(){
            fragment?.openCamera()
        }

        viewBinding.galleryButton.setOnClickListener(){
            fragment?.openGallery(requestCode)
        }

        viewBinding.mapButton.setOnClickListener(){
            fragment?.integranteMemberName(viewBinding.nameTextView.text.toString())
            fragment?.openMap()
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_name
    }
}