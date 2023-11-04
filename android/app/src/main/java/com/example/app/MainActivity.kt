package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app.model.InstrumentBrands
import com.example.app.model.InstrumentCategories
import com.example.app.model.MusicalInstruments
import com.example.app.model.Users
import com.example.app.model.db.AppDatabase
import com.example.app.navigation.Navigation
import com.example.app.repository.InstrumentBrandsRepository
import com.example.app.repository.InstrumentCategoriesRepository
import com.example.app.repository.MusicalInstrumentsRepository
import com.example.app.repository.OrdersRepository
import com.example.app.repository.UsersRepository
import com.example.app.service.InstrumentBrandsService
import com.example.app.service.InstrumentCategoriesService
import com.example.app.service.MusicalInstrumentsService
import com.example.app.service.OrdersService
import com.example.app.service.UsersService
import com.example.app.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
//        CoroutineScope(Dispatchers.IO).launch {
//            populateDB(appDatabase)
//        }
        setContent {
            AppTheme {
                Navigation(appDatabase = appDatabase)
            }
        }
    }

    private suspend fun populateDB(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val instrumentBrandsDao = appDatabase.instrumentBrandsDao()
        val instrumentCategoriesDao = appDatabase.instrumentCategoriesDao()
        val musicalInstrumentsDao = appDatabase.musicalInstrumentsDao()
        val ordersDao = appDatabase.ordersDao()
        val usersDao = appDatabase.usersDao()

        val instrumentBrandsService = InstrumentBrandsService(InstrumentBrandsRepository(instrumentBrandsDao))
        val instrumentCategoriesService = InstrumentCategoriesService(InstrumentCategoriesRepository(instrumentCategoriesDao))
        val musicalInstrumentsService = MusicalInstrumentsService(MusicalInstrumentsRepository(musicalInstrumentsDao))
        val ordersService = OrdersService(OrdersRepository(ordersDao))
        val usersService = UsersService(UsersRepository(usersDao))

        var instrumentBrandsList: List<InstrumentBrands> = mutableListOf()
        instrumentBrandsService.allInstrumentBrands.collect{brands ->
            instrumentBrandsList = brands

            if(instrumentBrandsList.isEmpty()){
                instrumentBrandsService.addInstrumentBrand(InstrumentBrands(0, "Fender", "https://assets.stickpng.com/images/585aad184f6ae202fedf2913.png"))
                instrumentBrandsService.addInstrumentBrand(InstrumentBrands(0, "Gibson", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Gibson_Guitar_logo.svg/1280px-Gibson_Guitar_logo.svg.png"))
                instrumentBrandsService.addInstrumentBrand(InstrumentBrands(0, "Ibanez", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Ibanez_logo.svg/2560px-Ibanez_logo.svg.png"))
            }

            var instrumentCategoriesList: List<InstrumentCategories> = mutableListOf()
            instrumentCategoriesService.allInstrumentCategories.collect{
                    categories -> instrumentCategoriesList = categories

                if(instrumentCategoriesList.isEmpty()){
                    instrumentCategoriesService.addInstrumentCategory(InstrumentCategories(0, "Electric Guitars", "https://thenounproject.com/api/private/icons/77928/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0"))
                    instrumentCategoriesService.addInstrumentCategory(InstrumentCategories(0, "Bass Guitars", "https://thenounproject.com/api/private/icons/367516/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0"))
                    instrumentCategoriesService.addInstrumentCategory(InstrumentCategories(0, "Keyboards", "https://cdn-icons-png.flaticon.com/512/1002/1002081.png"))
                }

                var musicalInstrumentsList: List<MusicalInstruments> = mutableListOf()
                musicalInstrumentsService.allMusicalInstruments.collect{
                        instruments -> musicalInstrumentsList = instruments

                    if(musicalInstrumentsList.isEmpty()){
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 1, "Fender Vintera II 60s Strat RW 3TS", "Body: Alder, Neck: Maple, Rosewood fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/571354.webp", 5444f, 23, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 1, "Fender Player Series Strat MN PWT", "Body: Alder, Neck: Maple, Rosewood fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/439056.webp", 3799f, 10, true))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 1, "Fender AM Ultra Tele MN Cobra Blue", "Body: Alder, Neck: Maple, Maple fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/477101.webp", 11390f, 5, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 2, "Gibson SG ´61 Standard VC", "Body: Mahogany, Neck: Mahogany, Rosewood fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/462531.webp", 9299f, 7, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 2, "Gibson ES-335 Figured Iced Tea", "Semi-hollow body shape, Neck: Mahogany, Rosewood fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/482463.webp", 19590f, 2, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 2, "Gibson Les Paul Standard 50s P90", "Body: Mahogany, Neck: Mahogany, Rosewood fretboard", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/462508.webp", 13690f, 5, true))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 3, "Ibanez PIA3761C", "Steve Vai Signature Model, Body: Alder", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/555556.webp", 15890f, 1, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 3, "Ibanez KIKO100-TRR", "Kiko Loureiro Signature Model, Body: Alder", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/546524.webp", 11890f, 4, true))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 1, 3, "Ibanez RG9PB-TGF", "Body: Nyatoh, Top: Burl Polar", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/557157.webp", 6198f, 5, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 1, "Fender Jaco PastoriusTribute Jazz 3SB", "Body: Alder, Neck: Maple, Fretboard: Rosewood", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/179948.webp", 20890f, 3, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 1, "Fender Am Pro II Jazz Bass RW MERC", "Body: Alder, Neck: Maple, Fretboard: Rosewood", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/500294.webp", 10090f, 6, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 1, "Fender Am Pro II Jazz Bass MN DK NIT", "Body: Alder, Neck: Maple, Fretboard: Rosewood", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/500412.webp", 9899f, 10, true))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 3, "Ibanez TMB30-MGR Talman Short Scale", "Body: Poplar, Neck: Maple, Fretboard: Purpleheart", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/457381.webp", 1090f, 30, true))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 3, "Ibanez TMB30-IV", "Body: Poplar, Neck: Maple, Fretboard: Purpleheart", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/387082.webp", 1090f, 20, false))
                        musicalInstrumentsService.addMusicalInstrument(MusicalInstruments(0, 2, 3, "Ibanez GSR180-BK", "Body: Poplar, Neck: Maple, Fretboard: Purpleheart", "https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/177146.webp", 948f, 10, true))

                    }

                    var usersList: List<Users> = mutableListOf()
                    usersService.allUsers.collect{
                        users -> usersList = users
                        if(usersList.isEmpty()){
                            usersService.addUser(Users(1, "username", "password"))
                        }
                    }
                }
            }
        }
    }
}