Feature: Posting feature

  Scenario: Check when a user posts a message, it displays on the twitter console
    Given A Twitter console
    When "Alice" posts the messages
    |LOL|
    Then These messages appear on the console
    |Alice -> LOL|