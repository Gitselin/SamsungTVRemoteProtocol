-----------------------------------------
JSON Structure Readme | v0.2
-----------------------------------------
(pænere formatering, starter med noter om finurlighederne i den structure)


null values represent a field that is variable like ID that is dependent on the setup and the response data that can be within a range

-----------------------------------------
Adding new Protocol Function
-----------------------------------------

Write the json file with the general definitions

Update the config file with the filename and key in KEY_LIST and LOAD_LIST -> they need to be in the same position for the two lists

In PrclAdapter update parseResponse() and parseRequest() switches with a case for the new function

