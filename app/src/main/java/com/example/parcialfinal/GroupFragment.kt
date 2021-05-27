package com.example.parcialfinal

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.xwray.groupie.GroupieAdapter
import kotlinx.android.synthetic.main.item_name.*

class GroupFragment : Fragment() {

    val REQUEST_CODE_PICKER_IMAGE = 15
    val REQUEST_CODE_CAMERA = 20
    val ciudadMontes = LatLng(4.597033, -74.104874)
    val salitre = LatLng(-7.2823,-40.4559)
    val pontevedra = LatLng(42.4336,-8.64805 )
    val bosa = LatLng(4.61706 ,-74.1899)

    lateinit var integranteMember: String

    var adapter = GroupieAdapter()

    private lateinit var nameItem1: NameItem
    private lateinit var nameItem2: NameItem
    private lateinit var nameItem3: NameItem
    private lateinit var nameItem4: NameItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_group, container, false)

        val binding = v.findViewById<RecyclerView>(R.id.groupRecyclerView)
        binding.layoutManager = LinearLayoutManager(requireContext())
        adapter.add(StudentItem("Student 1"))
        nameItem1 = NameItem("Diego Amador",
            "74606",
            "Ciudad Montes",
            R.drawable.amador,
            "int",
            null,
            null,
            this,
            1,
            ciudadMontes)
        adapter.add(nameItem1)

        adapter.add(StudentItem("Student 2"))
        nameItem2 = NameItem("Nicolas Garcia",
            "75776",
            "Salitre",
            R.drawable.garcia,
            "int",
            null,
            null,
            this,
            2,
            salitre)
        adapter.add(nameItem2)

        adapter.add(StudentItem("Student 3"))
        nameItem3 = NameItem("Rafael Pereira",
            "56258",
            "Pontevedra",
            R.drawable.pereira,
            "int",
            null,
            null,
            this,
            3,
            pontevedra)
        adapter.add(nameItem3)

        adapter.add(StudentItem("Student 4"))
        nameItem4 = NameItem("Cesar Canabal",
            "75326",
            "Bosa",
            R.drawable.canabal,
            "int",
            null,
            null,
            this,
            4,
            bosa)
        adapter.add(nameItem4)
        binding.adapter = adapter
        return v.rootView
    }

    fun openGallery(numGallery: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type= "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICKER_IMAGE + numGallery )
    }

    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    fun openMap(){
        lateinit var mapFrag: MapFragment
        if(integranteMember.equals("Diego Amador")) {
            mapFrag = MapFragment(ciudadMontes)
        }
        else if(integranteMember.equals("Nicolas Garcia")){
            mapFrag = MapFragment(salitre)
        }
        else if(integranteMember.equals("Rafael Pereira")){
            mapFrag = MapFragment(pontevedra)
        }
        else{
            mapFrag = MapFragment(bosa)
        }

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(id, mapFrag)
        transaction.commit()
    }

    fun integranteMemberName(member: String){
        integranteMember = member
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        when(requestCode){
            REQUEST_CODE_PICKER_IMAGE + 1  ->{
                if(resultCode == AppCompatActivity.RESULT_OK) {
                    val uri = intent?.data
                    nameItem1.uriImage = uri
                    nameItem1.image = "uri"
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Cambio de imagen exitoso", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"No seleccione una imagen", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_PICKER_IMAGE + 2 ->{
                if(resultCode == AppCompatActivity.RESULT_OK) {
                    val uri = intent?.data
                    nameItem2.uriImage = uri
                    nameItem2.image = "uri"
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Cambio de imagen exitoso", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"No seleccione una imagen", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_PICKER_IMAGE + 3 ->{
                if(resultCode == AppCompatActivity.RESULT_OK) {
                    val uri = intent?.data
                    nameItem3.uriImage = uri
                    nameItem3.image = "uri"
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Cambio de imagen exitoso", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"No seleccione una imagen", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_PICKER_IMAGE + 4 ->{
                if(resultCode == AppCompatActivity.RESULT_OK) {
                    val uri = intent?.data
                    nameItem4.uriImage = uri
                    nameItem4.image = "uri"
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Cambio de imagen exitoso", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"No seleccione una imagen", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_CAMERA ->{
                if(resultCode == AppCompatActivity.RESULT_OK) {
                    val bitmap = intent?.extras?.get("data") as Bitmap
                    studentImagenButton.setImageBitmap(bitmap)
                    Toast.makeText(requireContext(), "Foto tomada desde la camara", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"No tomo la fotografia", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}