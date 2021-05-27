package com.example.parcialfinal

import com.example.parcialfinal.data.model.Continents
import com.example.parcialfinal.databinding.ItemContinentsBinding
import com.xwray.groupie.databinding.BindableItem

class ContinentItem(private val continents: Continents): BindableItem<ItemContinentsBinding>() {
    override fun bind(viewBinding: ItemContinentsBinding, position: Int) {
        viewBinding.nameContinent.text = continents.name
        viewBinding.codeContinent.text = continents.code
        viewBinding.namelocaleContinent.text = continents.name_locale
        viewBinding.latitudeContinent.text = "Latitud: ${continents.latitude}"
        viewBinding.longitudeContinent.text = "Longitud: ${continents.longitude}"
    }

    override fun getLayout(): Int {
        return R.layout.item_continents
    }
}