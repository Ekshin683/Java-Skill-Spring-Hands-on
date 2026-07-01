//Automated testing: It is a process of testing uses software tools to run tests automatically without any human interaction.

//Three types:
//1.Unit testing: it makes code simpler and easier to maintain.
//2.Integrstion testing: it is a type of testing where individual units are combined and tested simultaneously.
//E2E testing: it runs application from start to end to check the application flow. Uses Selinium, typewrite etc..


//Three key frameworks:
//1.Data-driven-framework: Instead manually entering data, it uses external data sources like CSV, Excel, or databases to drive the test cases.
//2.Keyword-driven-framework: It uses keywords to represent actions or operations in the test cases.
//3.Hybrid-framework: It combines the feature of both data-driven and keyword-frameworks.

@ParameterizedTest
@CsvFileSource(resources = "/test-data/login-data.csv", numLinesToSkip = 1)
void shouldLoginWithVariousCredentials(String username, String password, boolean expectedSuccess) {
    boolean result = authService.login(username, password);
    assertEquals(expectedSuccess, result);
}