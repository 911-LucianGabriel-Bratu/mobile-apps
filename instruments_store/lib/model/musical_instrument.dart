class MusicalInstrument {
  late int musicalInstrumentID;
  late String musicalInstrumentName;
  late String description;
  late String pngUrl;
  late double price;
  late int quantity;
  late bool onSale;

  MusicalInstrument({
    required this.musicalInstrumentID,
    required this.musicalInstrumentName,
    required this.description,
    required this.pngUrl,
    required this.price,
    required this.quantity,
    required this.onSale,
  });
}