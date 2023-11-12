import 'package:flutter/material.dart';
import 'package:instruments_store/model/musical_instrument.dart';
import 'package:instruments_store/repository/musical_instruments_repository.dart';
import 'package:instruments_store/views/add_instrument.dart';
import 'package:instruments_store/views/delete_instrument.dart';
import 'package:instruments_store/views/update_instrument.dart';
import 'package:provider/provider.dart';
import '../Theme/color.dart';
import 'package:lazy_load_scrollview/lazy_load_scrollview.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePage();
}

class _HomePage extends State<HomePage>{
  int _loadedItems = 3;
  int _toLoad = 2;
  bool _finished = false;

  Future<void> loadMore() async {
    await Future.delayed(const Duration(milliseconds: 100));
    setState(() {
      if(!_finished){
        _loadedItems += _toLoad;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
          title: const Text("Products", textAlign: TextAlign.center),
          centerTitle: true
      ),
      body:
        Consumer<MusicalInstrumentRepository>(
          builder: (context, provider, child){
            List<MusicalInstrument> instruments = provider.instruments.cast<MusicalInstrument>();
            return Scaffold(
              backgroundColor: Theme.of(context).extension<CustomColors>()!.accent,
              body: LazyLoadScrollView(
                onEndOfPage: () => loadMore(),
                child: ListView.builder(
                    itemCount: _loadedItems,
                    itemBuilder: (context, position) {
                      if (position < instruments.length) {
                        if(instruments.length - position < _toLoad){
                          _toLoad = instruments.length - position;
                        }
                        return ListTile(
                          titleAlignment: ListTileTitleAlignment.center,
                          title: Text(
                            instruments[position].musicalInstrumentName,
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              fontFamily: 'Ubuntu',
                              fontSize: 24,
                              color: Theme.of(context).extension<CustomColors>()!.accentContainer
                            ),
                          ),
                          subtitle:
                          Column(
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Image.network(instruments[position].pngUrl),
                                Text(
                                    "Description: ${instruments[position].description}",
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        fontFamily: 'Ubuntu',
                                        fontSize: 24,
                                        color: Theme.of(context).extension<CustomColors>()!.accentContainer
                                    ),
                                ),
                                Text(
                                    "Price: ${instruments[position].price}",
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        fontFamily: 'Ubuntu',
                                        fontSize: 24,
                                        color: Theme.of(context).extension<CustomColors>()!.accentContainer
                                  ),
                                ),
                                Text(
                                    "Quantity: ${instruments[position].quantity}",
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        fontFamily: 'Ubuntu',
                                        fontSize: 24,
                                        color: Theme.of(context).extension<CustomColors>()!.accentContainer
                                  ),
                                ),
                                Text(
                                    "On Sale: ${instruments[position].onSale}",
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        fontFamily: 'Ubuntu',
                                        fontSize: 24,
                                        color: Theme.of(context).extension<CustomColors>()!.accentContainer,
                                  ),
                                ),
                                Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                    children: [
                                      FloatingActionButton(
                                        heroTag: "EditInstrumentBtn",
                                        onPressed: () {
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(builder: (context) => UpdateInstrumentView(musicalInstrument: instruments[position])));
                                        },
                                        tooltip: 'Edit product',
                                        backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                                        foregroundColor: Theme.of(context).extension<CustomColors>()!.accent,
                                        child: const Icon(Icons.edit),
                                      ),
                                      const SizedBox(width: 25),
                                      FloatingActionButton(
                                        heroTag: "RemoveInstrumentBtn",
                                        onPressed: () {
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(builder: (context) => DeletePage(musicalInstrument: instruments[position])));
                                        },
                                        tooltip: 'Remove product',
                                        backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                                        foregroundColor: Theme.of(context).extension<CustomColors>()!.accent,
                                        child: const Icon(Icons.delete),
                                      )
                                    ],
                                ),
                                const Padding(padding: EdgeInsets.fromLTRB(0, 10, 0, 10))
                              ]
                          )
                        );
                      } else {
                        _finished = true;
                      }
                    }),
              ),
              floatingActionButton: FloatingActionButton(
                onPressed: () => {
                  Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => const AddInstrumentView()))
                },
                tooltip: 'Add product',
                backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                heroTag: "addInstrumentBtn",
                child: const Icon(Icons.add),
              ),
            );
          },
        )
    );
  }
}