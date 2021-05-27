package com.example.parcialfinal

import com.example.parcialfinal.data.model.Country
import com.xwray.groupie.databinding.BindableItem
import com.example.parcialfinal.databinding.ItemCountryBinding

class CountryItem(private val country: Country, val callback: (String) -> Unit) : BindableItem<ItemCountryBinding>() {

    override fun bind(viewBinding: ItemCountryBinding, position: Int) {
            viewBinding.nameCountry.text = country.name
            viewBinding.alpha2CodeCountry.text = country.alpha2Code
            viewBinding.regionCountry.text = country.region
            viewBinding.subregionCountry.text = country.subregion
            viewBinding.populationCountry.text = "Población: ${country.population}"
            viewBinding.areaCountry.text = "Area: ${country.area}"

        viewBinding.detailButton.setOnClickListener {
            callback(country.subregion)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_country
    }
}