import 'package:flutter/material.dart';

import '../Theme/color.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
        title: const Text("Products", textAlign: TextAlign.center),
        centerTitle: true
      ),
      body: const Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Products list...',
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => {},
        tooltip: 'Add product',
        backgroundColor: Theme.of(context).extension<CustomColors>()!.primary,
        child: const Icon(Icons.add),
      ),
    );
  }

}