package com.example.sandbox

import indigo._

object SandboxAssets {

  val smallFontName: AssetName = AssetName("smallFontName")
  val dudeName: AssetName      = AssetName("base_charactor")
  val light: AssetName         = AssetName("light")
  val dots: AssetName          = AssetName("dots")

  val smallFontNameMaterial: StandardMaterial.Basic = StandardMaterial.Basic(smallFontName, 0.5)
  val lightMaterial: StandardMaterial.Basic         = StandardMaterial.Basic(light, 1.0)
  val dotsMaterial: StandardMaterial.Basic          = StandardMaterial.Basic(dots, 1.0)

  def assets: Set[AssetType] =
    Set(
      AssetType.Image(smallFontName, AssetPath("assets/boxy_font.png")),
      AssetType.Image(light, AssetPath("assets/light_texture.png")),
      AssetType.Text(AssetName(dudeName.value + "-json"), AssetPath("assets/" + dudeName.value + ".json")),
      AssetType.Image(dudeName, AssetPath("assets/" + dudeName.value + ".png")),
      AssetType.Image(dots, AssetPath("assets/" + dots.value + ".png"))
    )

}
