package com.example.parcialfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialfinal.data.RetrofitInstancesContinents
import com.example.parcialfinal.data.model.ResultContinents
import com.xwray.groupie.GroupieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContinentsServicesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_continents_services, container, false)

        val binding = v.findViewById<RecyclerView>(R.id.continentsRecyclerView)
        binding.layoutManager = LinearLayoutManager(requireContext())

        val adapter = GroupieAdapter()
        binding.adapter = adapter

        RetrofitInstancesContinents().continentsApi().getContinents("en").enqueue(object :
                Callback<ResultContinents> {
            override fun onResponse(call: Call<ResultContinents>, response: Response<ResultContinents>) {

                if(response.body() != null){
                    val countriesItem: ResultContinents = response.body()!!
                    for(c in countriesItem.data){
                        adapter.add(ContinentItem(c))
                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.commit()
                    }
                }else{
                    Toast.makeText(requireContext(), response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResultContinents>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }
        })
        return v.rootView
    }
}