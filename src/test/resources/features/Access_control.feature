Feature: Access control user management
        Use cases for app access control

  @valid_login
  Scenario: As a user, I should be able to login
    Given I am on the login page
    And login page components exist
    When I enter valid username and valid password
    And I click login button
    Then I should be on the Dashboard page
    And the success message displays


  @valid_login_variable
  Scenario: As a user, I should be able to login
    Given I am on the login page
    And login page components exist
    When I enter valid username and valid password
    And I click login button
    Then I should be on the " Dashboard" page
    And "Success!" message displays
