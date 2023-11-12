import 'package:flutter/material.dart';
import 'package:instruments_store/model/musical_instrument.dart';
import 'package:provider/provider.dart';

import '../Theme/color.dart';
import '../repository/musical_instruments_repository.dart';

class DeletePage extends StatelessWidget{
  final MusicalInstrument musicalInstrument;

  const DeletePage({super.key, required this.musicalInstrument});

  @override
  Widget build(BuildContext context) {
    final provider = Provider.of<MusicalInstrumentRepository>(context, listen: false);

    return Scaffold(
      backgroundColor: Theme.of(context).extension<CustomColors>()!.accent,
      body: Center(
        child: Column(
          children: [
            const Padding(padding: EdgeInsets.fromLTRB(0, 100, 0, 0)),
            Text("Are you sure you want to delete this product?",
                style: TextStyle(
                    fontSize: 32, color: Theme.of(context).extension<CustomColors>()!.onAccent),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 20),
            Column(
              children: [
                Image.network(musicalInstrument.pngUrl),
                Text(
                  "Description: ${musicalInstrument.description}",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                      fontFamily: 'Ubuntu',
                      fontSize: 24,
                      color: Theme.of(context).extension<CustomColors>()!.accentContainer
                  ),
                ),
                Text(
                  "Price: ${musicalInstrument.price}",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                      fontFamily: 'Ubuntu',
                      fontSize: 24,
                      color: Theme.of(context).extension<CustomColors>()!.accentContainer
                  ),
                ),
                Text(
                  "Quantity: ${musicalInstrument.quantity}",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                      fontFamily: 'Ubuntu',
                      fontSize: 24,
                      color: Theme.of(context).extension<CustomColors>()!.accentContainer
                  ),
                ),
                Text(
                  "On Sale: ${musicalInstrument.onSale}",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontFamily: 'Ubuntu',
                    fontSize: 24,
                    color: Theme.of(context).extension<CustomColors>()!.accentContainer,
                  ),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                FloatingActionButton(
                  heroTag: "SubmitBtn",
                  onPressed: () {
                      provider.deleteInstrument(musicalInstrument);
                      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Deletion successful")));
                      Navigator.pop(context);
                  },
                  tooltip: 'Submit',
                  backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                  foregroundColor: Theme.of(context).extension<CustomColors>()!.accent,
                  child: const Icon(Icons.check),
                ),
                const SizedBox(width: 25),
                FloatingActionButton(
                  heroTag: "CancelBtn",
                  onPressed: () {
                    Navigator.pop(context);
                  },
                  tooltip: 'Cancel',
                  backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                  foregroundColor: Theme.of(context).extension<CustomColors>()!.accent,
                  child: const Icon(Icons.close),
                )
              ],
            )
          ],
        ),
      ),
    );
  }

}