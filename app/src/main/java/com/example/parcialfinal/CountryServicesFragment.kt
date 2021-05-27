package com.example.parcialfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialfinal.data.RetrofitInstance
import com.example.parcialfinal.data.model.Country

class CountryServicesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country_services, container, false)

        val binding = v.findViewById<RecyclerView>(R.id.countriesRecyclerView)
        binding.layoutManager = LinearLayoutManager(requireContext())

        val adapter = GroupieAdapter()
        binding.adapter = adapter

        RetrofitInstance().countryApi().getCountries().enqueue(object :
            Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                response.body()?.let {list ->
                    val countriesItem: List<CountryItem> = list.sortedBy {it.name}.map{ CountryItem(it){ code ->
                            val continent = ContinentsServicesFragment()
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.replace(id, continent)
                            transaction.commit()
                        }
                    }
                    adapter.update(countriesItem)
                } ?: run {
                    if (response.code() == 401) {
                        Toast.makeText(requireContext(), "El servicio no tiene el token", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "El servicio ha fallado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }})
        return v.rootView
    }
}