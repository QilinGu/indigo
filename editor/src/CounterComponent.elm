module CounterComponent exposing (model, view, update, CounterModel, CounterUpdateMsg)

import Html exposing (..)
import Html.Events exposing (onClick, onInput)
import Html.Attributes exposing (..)


-- Config definitions

type alias CounterModel = Int

-- Model

model : CounterModel
model =
  0

-- Update

type CounterUpdateMsg =
  IncrementCounter
  | DecrementCounter
  | InputCounter String

clampValue : Int -> Int
clampValue newValue =
  Basics.max 1 (Basics.min 10 newValue)

update : CounterUpdateMsg -> CounterModel -> CounterModel
update msg model =
  case msg of
    IncrementCounter ->
      clampValue model + 1

    DecrementCounter ->
      clampValue model - 1

    InputCounter str ->
      clampValue (Result.withDefault 1 (String.toInt str))


-- View

view : String -> CounterModel -> Html CounterUpdateMsg
view label model =
  div []
    [ text label
    , button [ onClick IncrementCounter ] [ text "+" ]
    , input [ value (toString model), onInput InputCounter ] []
    , button [ onClick DecrementCounter ] [ text "-" ]
    ]