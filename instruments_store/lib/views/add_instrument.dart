import 'package:flutter/material.dart';
import 'package:instruments_store/repository/musical_instruments_repository.dart';
import 'package:provider/provider.dart';

import '../model/musical_instrument.dart';
import '../Theme/color.dart';

class AddInstrumentView extends StatefulWidget {
  const AddInstrumentView({Key? key}) : super(key: key);

  @override
  State<AddInstrumentView> createState() => _AddInstrumentViewState();
}

class _AddInstrumentViewState extends State<AddInstrumentView> {
  TextEditingController nameController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();
  TextEditingController pngUrlController = TextEditingController();
  TextEditingController priceController = TextEditingController();
  TextEditingController quantityController = TextEditingController();
  bool onSaleValue = false;
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).extension<CustomColors>()!.accent,
      appBar: AppBar(
        title: const Text('Add Instrument'),
        backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                TextFormField(
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter the instrument name';
                    }
                    return null;
                  },
                  style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                  controller: nameController,
                  decoration: InputDecoration(
                    focusColor: Theme.of(context).extension<CustomColors>()!.primary,
                    labelStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                      labelText: 'Instrument Name',
                  ),
                ),
                TextFormField(
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter the description';
                    }
                    return null;
                  },
                  style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                  controller: descriptionController,
                  decoration: InputDecoration(
                    focusColor: Theme.of(context).extension<CustomColors>()!.primary,
                    labelStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                    labelText: 'Description',
                  ),
                ),
                TextFormField(
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter the image URL';
                    }
                    return null;
                  },
                  style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                  controller: pngUrlController,
                  decoration: InputDecoration(
                    focusColor: Theme.of(context).extension<CustomColors>()!.primary,
                    labelStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                    labelText: 'Image URL',
                  ),
                ),
                TextFormField(
                  validator: (value) {
                    if (value == null || double.parse(value) == 0) {
                      return 'Please enter a valid number';
                    }
                    return null;
                  },
                  style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                  controller: priceController,
                  decoration: InputDecoration(
                    focusColor: Theme.of(context).extension<CustomColors>()!.primary,
                    labelStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                    labelText: 'Price',
                  ),
                ),
                TextFormField(
                  validator: (value) {
                    if (value == null || int.parse(value) == 0) {
                      return 'Please enter a valid number';
                    }
                    return null;
                  },
                  style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                  controller: quantityController,
                  decoration: InputDecoration(
                    focusColor: Theme.of(context).extension<CustomColors>()!.primary,
                    labelStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary),
                    labelText: 'Quantity',
                  ),
                ),
                const SizedBox(height: 20),
                // Dropdown for onSale
                DropdownButton<bool>(
                  value: onSaleValue,
                  dropdownColor: Theme.of(context).extension<CustomColors>()!.accent,
                  onChanged: (bool? newValue) {
                    if (newValue != null) {
                      // Update the state when the user selects an option
                      setState(() {
                        onSaleValue = newValue;
                      });
                    }
                  },
                  items: [
                    DropdownMenuItem<bool>(
                      value: true,
                      child: Text('On Sale', style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary)),
                    ),
                    DropdownMenuItem<bool>(
                      value: false,
                      child: Text('Not On Sale', style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.primary)),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
                    textStyle: TextStyle(color: Theme.of(context).extension<CustomColors>()!.accent)
                  ),
                  onPressed: () {
                    if(_formKey.currentState?.validate() ?? false){
                      final provider = Provider.of<MusicalInstrumentRepository>(context, listen: false);

                      // Create a new instrument with the provided values
                      MusicalInstrument newInstrument = MusicalInstrument(
                        musicalInstrumentID: 0,
                        musicalInstrumentName: nameController.text,
                        description: descriptionController.text,
                        pngUrl: pngUrlController.text,
                        price: double.parse(priceController.text),
                        quantity: int.parse(quantityController.text),
                        onSale: onSaleValue,
                      );

                      // Add the new instrument to the provider
                      provider.addInstrument(newInstrument);

                      // Navigate back to the homepage
                      Navigator.pop(context);
                    }
                  },
                  child: Text('Add Instrument', style: TextStyle(color: Theme.of(context).extension<CustomColors>()!.accent))
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}