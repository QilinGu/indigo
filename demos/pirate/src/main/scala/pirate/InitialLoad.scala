package pirate

import indigo._
import indigo.json.Json
import indigoexts.formats._

object InitialLoad {

  def setup(assetCollection: AssetCollection): Startup[StartupErrors, StartupData] = {

    val loadedReflections: Option[SpriteAndAnimations] = for {
      json                <- assetCollection.findTextDataByName(AssetName(Assets.Water.jsonRef))
      aseprite            <- Json.asepriteFromJson(json)
      spriteAndAnimations <- AsepriteConverter.toSpriteAndAnimations(aseprite, Depth(20), Assets.Water.ref)
    } yield spriteAndAnimations

    val loadedFlag: Option[SpriteAndAnimations] = for {
      json                <- assetCollection.findTextDataByName(AssetName(Assets.Flag.jsonRef))
      aseprite            <- Json.asepriteFromJson(json)
      spriteAndAnimations <- AsepriteConverter.toSpriteAndAnimations(aseprite, Depth(10), Assets.Flag.ref)
    } yield spriteAndAnimations

    val loadedHelm: Option[SpriteAndAnimations] = for {
      json                <- assetCollection.findTextDataByName(AssetName(Assets.Helm.jsonRef))
      aseprite            <- Json.asepriteFromJson(json)
      spriteAndAnimations <- AsepriteConverter.toSpriteAndAnimations(aseprite, Depth(9), Assets.Helm.ref)
    } yield spriteAndAnimations

    val loadedCaptain: Option[SpriteAndAnimations] = for {
      json                <- assetCollection.findTextDataByName(AssetName(Assets.Captain.jsonRef))
      aseprite            <- Json.asepriteFromJson(json)
      spriteAndAnimations <- AsepriteConverter.toSpriteAndAnimations(aseprite, Depth(2), Assets.Captain.ref)
    } yield spriteAndAnimations

    (loadedReflections, loadedFlag, loadedCaptain, loadedHelm) match {
      case (Some(reflections), Some(flag), Some(captain), Some(helm)) =>
        makeStartupData(reflections, flag, captain, helm)

      case (None, _, _, _) =>
        Startup.Failure(StartupErrors("Failed to load the water reflections"))

      case (_, None, _, _) =>
        Startup.Failure(StartupErrors("Failed to load the flag"))

      case (_, _, None, _) =>
        Startup.Failure(StartupErrors("Failed to load the captain"))

      case (_, _, _, None) =>
        Startup.Failure(StartupErrors("Failed to load the helm"))

    }
  }

  def makeStartupData(waterReflections: SpriteAndAnimations, flag: SpriteAndAnimations, captain: SpriteAndAnimations, helm: SpriteAndAnimations): Startup.Success[StartupData] =
    Startup
      .Success(
        StartupData(waterReflections.sprite, flag.sprite, captain.sprite, helm.sprite)
      )
      .addAnimations(waterReflections.animations, flag.animations, captain.animations, helm.animations)

}

final case class StartupData(waterReflections: Sprite, flag: Sprite, captain: Sprite, helm: Sprite)
