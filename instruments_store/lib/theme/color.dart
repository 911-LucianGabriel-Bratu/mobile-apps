import 'dart:ui';

import 'package:flutter/material.dart';

const lightOrangeDark = Color(0xFFD06400);
const darkOrangeDark = Color(0xFFA75000);
const blackDark = Color(0xFF000000);
const whiteDark = Color(0xFFFFFFFF);

const lightOrangeLight = Color(0xFFD06400);
const darkOrangeLight = Color(0xFFA75000);
const blackLight = Color(0xFFFFFFFF);
const whiteLight = Color(0xFFFFFFFF);

CustomColors lightCustomColors = const CustomColors(
  sourceDarkgray: Color(0xFF6A6A6A),
  darkgray: Color(0xFF006874),
  onDarkgray: Color(0xFFFFFFFF),
  darkgrayContainer: Color(0xFF97F0FF),
  onDarkgrayContainer: Color(0xFF001F24),
  sourcePrimary: Color(0xFF2541B2),
  primary: Color(0xFFD06400),
  onPrimary: Color(0xFFFFFFFF),
  primaryContainer: Color(0xFFDDE1FF),
  onPrimaryContainer: Color(0xFF001257),
  sourceSecondary: Color(0xFF007BFF),
  secondary: Color(0xFFA75000),
  onSecondary: Color(0xFFFFFFFF),
  secondaryContainer: Color(0xFFD8E2FF),
  onSecondaryContainer: Color(0xFF001A41),
  accent: Color(0xFF000000),
  sourceAccent: Color(0xFF855400),
  onAccent: Color(0xFFFFFFFF),
  accentContainer: Color(0xFFFFFFFF),
  onAccentContainer: Color(0xFF2A1700),
  sourceLightgray: Color(0xFFCCCCCC),
  lightgray: Color(0xFF006874),
  onLightgray: Color(0xFFFFFFFF),
  lightgrayContainer: Color(0xFF97F0FF),
  onLightgrayContainer: Color(0xFF001F24),
  confirmGreen: Color(0xFF14A800),
  deleteRed: Color(0xFFE00000),
);

CustomColors darkCustomColors = const CustomColors(
  sourceDarkgray: Color(0xFF6A6A6A),
  darkgray: Color(0xFF4FD8EB),
  onDarkgray: Color(0xFF00363D),
  darkgrayContainer: Color(0xFF004F58),
  onDarkgrayContainer: Color(0xFF97F0FF),
  sourcePrimary: Color(0xFF2541B2),
  primary: Color(0xFFB9C3FF),
  onPrimary: Color(0xFF00228A),
  primaryContainer: Color(0xFF1C3AAC),
  onPrimaryContainer: Color(0xFFDDE1FF),
  sourceSecondary: Color(0xFF007BFF),
  secondary: Color(0xFFADC7FF),
  onSecondary: Color(0xFF002E68),
  secondaryContainer: Color(0xFF004493),
  onSecondaryContainer: Color(0xFFD8E2FF),
  sourceAccent: Color(0xFFED9900),
  accent: Color(0xFFFFB95C),
  onAccent: Color(0xFF462A00),
  accentContainer: Color(0xFF653E00),
  onAccentContainer: Color(0xFFFFDDB7),
  sourceLightgray: Color(0xFFCCCCCC),
  lightgray: Color(0xFF4FD8EB),
  onLightgray: Color(0xFF00363D),
  lightgrayContainer: Color(0xFF004F58),
  onLightgrayContainer: Color(0xFF97F0FF),
  confirmGreen: Color(0xFF14A800),
  deleteRed: Color(0xFFE00000),
);



/// Defines a set of custom colors, each comprised of 4 complementary tones.
///
/// See also:
///   * <https://m3.material.io/styles/color/the-color-system/custom-colors>
@immutable
class CustomColors extends ThemeExtension<CustomColors> {
  const CustomColors({
    required this.sourceDarkgray,
    required this.darkgray,
    required this.onDarkgray,
    required this.darkgrayContainer,
    required this.onDarkgrayContainer,
    required this.sourcePrimary,
    required this.primary,
    required this.onPrimary,
    required this.primaryContainer,
    required this.onPrimaryContainer,
    required this.sourceSecondary,
    required this.secondary,
    required this.onSecondary,
    required this.secondaryContainer,
    required this.onSecondaryContainer,
    required this.sourceAccent,
    required this.accent,
    required this.onAccent,
    required this.accentContainer,
    required this.onAccentContainer,
    required this.sourceLightgray,
    required this.lightgray,
    required this.onLightgray,
    required this.lightgrayContainer,
    required this.onLightgrayContainer,
    required this.confirmGreen,
    required this.deleteRed,
  });

  final Color? sourceDarkgray;
  final Color? darkgray;
  final Color? onDarkgray;
  final Color? darkgrayContainer;
  final Color? onDarkgrayContainer;
  final Color? sourcePrimary;
  final Color? primary;
  final Color? onPrimary;
  final Color? primaryContainer;
  final Color? onPrimaryContainer;
  final Color? sourceSecondary;
  final Color? secondary;
  final Color? onSecondary;
  final Color? secondaryContainer;
  final Color? onSecondaryContainer;
  final Color? sourceAccent;
  final Color? accent;
  final Color? onAccent;
  final Color? accentContainer;
  final Color? onAccentContainer;
  final Color? sourceLightgray;
  final Color? lightgray;
  final Color? onLightgray;
  final Color? lightgrayContainer;
  final Color? onLightgrayContainer;
  final Color? confirmGreen;
  final Color? deleteRed;

  @override
  CustomColors copyWith({
    Color? sourceDarkgray,
    Color? darkgray,
    Color? onDarkgray,
    Color? darkgrayContainer,
    Color? onDarkgrayContainer,
    Color? sourcePrimary,
    Color? primary,
    Color? onPrimary,
    Color? primaryContainer,
    Color? onPrimaryContainer,
    Color? sourceSecondary,
    Color? secondary,
    Color? onSecondary,
    Color? secondaryContainer,
    Color? onSecondaryContainer,
    Color? sourceAccent,
    Color? accent,
    Color? onAccent,
    Color? accentContainer,
    Color? onAccentContainer,
    Color? sourceLightgray,
    Color? lightgray,
    Color? onLightgray,
    Color? lightgrayContainer,
    Color? onLightgrayContainer,
    Color? confirmGreen,
    Color? deleteRed,
  }) {
    return CustomColors(
      sourceDarkgray: sourceDarkgray ?? this.sourceDarkgray,
      darkgray: darkgray ?? this.darkgray,
      onDarkgray: onDarkgray ?? this.onDarkgray,
      darkgrayContainer: darkgrayContainer ?? this.darkgrayContainer,
      onDarkgrayContainer: onDarkgrayContainer ?? this.onDarkgrayContainer,
      sourcePrimary: sourcePrimary ?? this.sourcePrimary,
      primary: primary ?? this.primary,
      onPrimary: onPrimary ?? this.onPrimary,
      primaryContainer: primaryContainer ?? this.primaryContainer,
      onPrimaryContainer: onPrimaryContainer ?? this.onPrimaryContainer,
      sourceSecondary: sourceSecondary ?? this.sourceSecondary,
      secondary: secondary ?? this.secondary,
      onSecondary: onSecondary ?? this.onSecondary,
      secondaryContainer: secondaryContainer ?? this.secondaryContainer,
      onSecondaryContainer: onSecondaryContainer ?? this.onSecondaryContainer,
      sourceAccent: sourceAccent ?? this.sourceAccent,
      accent: accent ?? this.accent,
      onAccent: onAccent ?? this.onAccent,
      accentContainer: accentContainer ?? this.accentContainer,
      onAccentContainer: onAccentContainer ?? this.onAccentContainer,
      sourceLightgray: sourceLightgray ?? this.sourceLightgray,
      lightgray: lightgray ?? this.lightgray,
      onLightgray: onLightgray ?? this.onLightgray,
      lightgrayContainer: lightgrayContainer ?? this.lightgrayContainer,
      onLightgrayContainer: onLightgrayContainer ?? this.onLightgrayContainer,
      confirmGreen: confirmGreen ?? this.confirmGreen,
      deleteRed: deleteRed ?? this.deleteRed,
    );
  }

  @override
  CustomColors lerp(ThemeExtension<CustomColors>? other, double t) {
    if (other is! CustomColors) {
      return this;
    }
    return CustomColors(
      sourceDarkgray: Color.lerp(sourceDarkgray, other.sourceDarkgray, t),
      darkgray: Color.lerp(darkgray, other.darkgray, t),
      onDarkgray: Color.lerp(onDarkgray, other.onDarkgray, t),
      darkgrayContainer: Color.lerp(darkgrayContainer, other.darkgrayContainer, t),
      onDarkgrayContainer: Color.lerp(onDarkgrayContainer, other.onDarkgrayContainer, t),
      sourcePrimary: Color.lerp(sourcePrimary, other.sourcePrimary, t),
      primary: Color.lerp(primary, other.primary, t),
      onPrimary: Color.lerp(onPrimary, other.onPrimary, t),
      primaryContainer: Color.lerp(primaryContainer, other.primaryContainer, t),
      onPrimaryContainer: Color.lerp(onPrimaryContainer, other.onPrimaryContainer, t),
      sourceSecondary: Color.lerp(sourceSecondary, other.sourceSecondary, t),
      secondary: Color.lerp(secondary, other.secondary, t),
      onSecondary: Color.lerp(onSecondary, other.onSecondary, t),
      secondaryContainer: Color.lerp(secondaryContainer, other.secondaryContainer, t),
      onSecondaryContainer: Color.lerp(onSecondaryContainer, other.onSecondaryContainer, t),
      sourceAccent: Color.lerp(sourceAccent, other.sourceAccent, t),
      accent: Color.lerp(accent, other.accent, t),
      onAccent: Color.lerp(onAccent, other.onAccent, t),
      accentContainer: Color.lerp(accentContainer, other.accentContainer, t),
      onAccentContainer: Color.lerp(onAccentContainer, other.onAccentContainer, t),
      sourceLightgray: Color.lerp(sourceLightgray, other.sourceLightgray, t),
      lightgray: Color.lerp(lightgray, other.lightgray, t),
      onLightgray: Color.lerp(onLightgray, other.onLightgray, t),
      lightgrayContainer: Color.lerp(lightgrayContainer, other.lightgrayContainer, t),
      onLightgrayContainer: Color.lerp(onLightgrayContainer, other.onLightgrayContainer, t),
      confirmGreen: Color.lerp(confirmGreen, other.confirmGreen, t),
      deleteRed: Color.lerp(deleteRed, other.deleteRed, t),
    );
  }

  /// Returns an instance of [CustomColors] in which the following custom
  /// colors are harmonized with [dynamic]'s [ColorScheme.primary].
  ///
  /// See also:
  ///   * <https://m3.material.io/styles/color/the-color-system/custom-colors#harmonization>
  CustomColors harmonized(ColorScheme dynamic) {
    return copyWith(
    );
  }
}