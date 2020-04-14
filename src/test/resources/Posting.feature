Feature: Posting feature

  Scenario: Check that messages posted by users display on the twitter console
    Given A Twitter console
    When "Alice" posts the messages
      | Yesterday 24 degrees... |
      | Today, 8!               |
    And "Bob" posts the messages
      | Loving this weather |
    And "Bob" reads "Alice" timeline
    Then These messages appear on the console
      | Alice -> Yesterday 24 degrees...        |
      | Alice -> Today, 8!                      |
      | Bob -> Loving this weather              |
      | Alice                                   |
      | Today, 8! (0 seconds ago)               |
      | Yesterday 24 degrees... (0 seconds ago) |