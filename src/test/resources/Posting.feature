Feature: Posting feature

  Scenario: Check that messages posted by users display on the twitter console
    Given A Twitter console
    When "Alice" posts the messages
    |LOL|
    |This coronavirus is crazy!|
    And "Bob" posts the messages
      |Loving this weather|
    Then These messages appear on the console
    |Alice -> LOL|
    |Alice -> This coronavirus is crazy!|
    |Bob -> Loving this weather         |