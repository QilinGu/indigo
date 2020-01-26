package pirate

import indigo._

object View {

  def present(model: Model, viewModel: ViewModel): SceneUpdateFragment =
    SceneUpdateFragment.empty
      .addGameLayerNodes(Assets.backgroundGraphic)
      .addGameLayerNodes(
        viewModel.waterReflections.play(),
        viewModel.waterReflections.moveBy(150, 30).play(),
        viewModel.waterReflections.moveBy(-100, 60).play(),
        viewModel.flag.play(),
        viewModel.helm.play(),
        Assets.chestGraphic.moveTo(380, 271),
        Assets.levelGraphic,
        drawPirate(model, viewModel.captain)
      )
      .withAudio(
        SceneAudio(
          SceneAudioSource(
            BindingKey(Assets.shanty),
            PlaybackPattern.SingleTrackLoop(
              Track(Assets.shanty)
            )
          )
        )
      )

  def drawPirate(model: Model, captain: Sprite): Sprite =
    model.pirateState match {
      case PirateState.Idle =>
        captain
          .moveTo(model.position)
          .changeCycle(CycleLabel("Idle"))
          .play()

      case PirateState.Jump =>
        captain
          .moveTo(model.position)
          .changeCycle(CycleLabel("Jump"))
          .play()

      case PirateState.MoveLeft =>
        captain
          .moveTo(model.position)
          .flipHorizontal(true)
          .moveBy(-20, 0)
          .changeCycle(CycleLabel("Run"))
          .play()

      case PirateState.MoveRight =>
        captain
          .moveTo(model.position)
          .changeCycle(CycleLabel("Run"))
          .play()

      case PirateState.Falling =>
        captain
          .moveTo(model.position)
          .changeCycle(CycleLabel("Fall"))
          .play()
    }

}
