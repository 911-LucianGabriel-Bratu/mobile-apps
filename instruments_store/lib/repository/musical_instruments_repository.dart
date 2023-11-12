import 'package:flutter/cupertino.dart';

import '../model/musical_instrument.dart';

class MusicalInstrumentRepository extends ChangeNotifier{
  List<MusicalInstrument> _instruments = [];
  int _lastID = 1;

  MusicalInstrumentRepository() {
    _hardcodeEntries();
  }

  List<MusicalInstrument> get instruments => _instruments;

  void addInstrument(MusicalInstrument instrument) {
    instrument.musicalInstrumentID = _lastID;
    _lastID += 1;
    _instruments.add(instrument);
    notifyListeners();
  }

  void updateInstrument(MusicalInstrument updatedInstrument){
    int index = _instruments.indexWhere((instrument) => instrument.musicalInstrumentID == updatedInstrument.musicalInstrumentID);
    if (index != -1) {
      _instruments[index] = updatedInstrument;
      notifyListeners();
    }
  }

  void deleteInstrument(MusicalInstrument instrument){
    if(instruments.contains(instrument)){
      instruments.remove(instrument);
    }
    notifyListeners();
  }

  void _hardcodeEntries() {
    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 1,
        musicalInstrumentName: 'Guitar',
        description: 'A six-stringed musical instrument',
        pngUrl: 'https://i.pinimg.com/736x/5c/9f/71/5c9f71b2e196ccf71e72eb88b9f75be8.jpg',
        price: 299.99,
        quantity: 10,
        onSale: true,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );

    _instruments.add(
      MusicalInstrument(
        musicalInstrumentID: 4,
        musicalInstrumentName: 'Piano',
        description: 'A keyboard instrument',
        pngUrl: 'https://static.vecteezy.com/system/resources/previews/013/442/267/original/realistic-black-grand-piano-musical-instrument-3d-rendering-icon-on-transparent-background-png.png',
        price: 999.99,
        quantity: 5,
        onSale: false,
      ),
    );
  }
}