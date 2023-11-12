import 'package:flutter/material.dart';
import 'package:instruments_store/Theme/theme.dart';
import 'package:instruments_store/Views/home_page.dart';
import 'package:instruments_store/repository/musical_instruments_repository.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
        create: (context) => MusicalInstrumentRepository(),
        child: const MyApp()
    )
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Instruments Store',
      theme: appTheme,
      home: const HomePage()
    );
  }
}
