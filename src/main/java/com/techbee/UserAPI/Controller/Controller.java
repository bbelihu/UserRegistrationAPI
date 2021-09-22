package com.techbee.UserAPI.Controller;

import com.techbee.UserAPI.Model.User;
import com.techbee.UserAPI.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String startpage() {
        return "Welcome to the User Registration Program";
    }


    @PostMapping(value = "/saveUser")
    public String saveUserInfo(@RequestBody User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmailAddress();
        int houseNumber = user.getHouseNumber();
        String streetName = user.getStreetName();
        String stateName = user.getStateName();
        int postalCode = user.getPostalCode();
        //FirstName, LastName verification
        firstName = nameVerfication(firstName);
        lastName = nameVerfication(lastName);
        if(firstName.equals("-1")|| lastName.equals("-1")){
            return "Illegal name: name can't have alphanumeric number, number or can't be empty";
        }
        //Phone number verification
        phoneNumber = phoneVerfication(phoneNumber);
        if(phoneNumber.equals("-1")){
            return "Illegal phone Number";
        }
        //Email verification format
        email = emailVerification(email);
        if(email.equals("-1")){
            return "Illegal email format";
        }
        stateName = stateNameVerification(stateName);
        if(stateName.equals("-1")){
            return "Illegal stateName, state Name doesn't exist";
        }
        String post= postalCodeVerification(postalCode);
        if(post.equals("-1")){
            return "Postal code can only be five digits";
        }
        else{
            postalCode = Integer.parseInt(post);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(email);
        user.setHouseNumber(houseNumber);
        user.setStreetName(streetName);
        user.setStateName(stateName);
        user.setPostalCode(postalCode);

        userRepo.save(user);
        return "User added";
    }

    @GetMapping(value = "/users")
    public List<User> get() {
        return userRepo.findAll();
    }


    @PutMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User userTarget = userRepo.findById(id).get();
        userTarget.setFirstName(user.getFirstName());
        userTarget.setLastName(user.getLastName());
        userTarget.setPhoneNumber(user.getPhoneNumber());
        userTarget.setEmailAddress(user.getEmailAddress());
        userTarget.setHouseNumber(user.getHouseNumber());
        userTarget.setStreetName(user.getStreetName());
        userTarget.setStateName(user.getStateName());
        userTarget.setPostalCode(user.getPostalCode());
        userRepo.save(userTarget);
        return "Updated User";
    }

    public static String nameVerfication(String name){
        char [] arr = name.toCharArray();
        for(int i=0; i< arr.length; ++i){
            if(Character.isDigit(arr[i])){
                return "-1";
            }
            if(!Character.isLetterOrDigit(arr[i])){
                return "-1";
            }
        }
        //Cant have empty String or have more than 24 characters.
        if(name.isEmpty()){
           return "-1";
        }else if(name.length()>24){
            return "-1";
        }else{
            return name;
        }
    }

    public static String phoneVerfication(String phone){
        char [] arr = phone.toCharArray();
        for(int i=0; i< arr.length; ++i){
            if(Character.isLetter(arr[i])){
               return "-1";

            }
            if(!Character.isLetterOrDigit(arr[i])){
               return "-1";

            }
        }

        if(phone.isEmpty()){
            return "-1";
        }else if(phone.length()>10|| phone.length()<10){
            return "-1";
        }else{
            String new_phone="+1-";
            for(int i=0;i<10;i++){
                new_phone+= phone.charAt(i);
                if(i==2 || i==5){
                    new_phone+="-";
                }
            }
            return new_phone;
        }
    }

    public static String emailVerification(String email){
        boolean comFlag = email.endsWith(".com");
        boolean eduFlag = email.endsWith(".edu");
        boolean govFlag = email.endsWith(".gov");
        boolean orgFlag = email.endsWith(".org");


        if(email.isEmpty()){
            return"-1";
        }
        else if(email.contains("@") == false) {
          return"-1";

        }else if(comFlag&&eduFlag&&orgFlag&&govFlag){
           return"-1";
        }
        else{
            return email;
        }
    }


    @DeleteMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        User userTarget = userRepo.findById(id).get();
        userRepo.delete(userTarget);
        return "User Deleted";
    }


    private static String stateNameVerification(String stateName){
        switch(stateName.toLowerCase()){
            case "alabama": return stateName;
            case "alaska": return stateName;
            case "arizona": return stateName;
            case"arkansas": return stateName;
            case "california": return stateName;
            case "colorado": return stateName;
            case "connecticut": return stateName;
            case "delware": return stateName;
            case "florida": return stateName;
            case "georgia": return stateName;
            case "hawaii": return stateName;
            case "idaho": return stateName;
            case "illinois": return stateName;
            case "indiana": return stateName;
            case "iowa": return stateName;
            case "kansas": return stateName;
            case "kentucky": return stateName ;
            case "louisiana": return stateName ;
            case "maine": return stateName;
            case "maryland": return stateName;
            case "massachusetts": return stateName;
            case "michigan": return stateName;
            case "minnesota": return stateName;
            case "mississippi": return stateName;
            case "missouri": return stateName;
            case "montana": return stateName;
            case "nebraska": return stateName;
            case "nevada": return stateName;
            case "new hampshire": return stateName;
            case "new jersey": return stateName;
            case "new mexico": return stateName;
            case "new york": return stateName;
            case "north carolina": return stateName;
            case "north dakota": return stateName;
            case "ohio": return stateName;
            case "oklahoma": return stateName;
            case "oregon": return stateName;
            case "pennsylvania": return stateName;
            case "rhode island": return stateName;
            case "south carolina":return stateName;
            case "south dakota": return stateName;
            case "tennessee": return stateName;
            case "texas": return stateName;
            case "utah": return stateName;
            case "vermont": return stateName;
            case "virginia":return stateName;
            case "washington": return stateName;
            case "west virginia": return stateName;
            case "wisconsin": return stateName;
            case "wyoming": return stateName;
            default:
                return "-1";
        }
    }
    private static String postalCodeVerification(int postal){
        String num = Integer.toString(postal);
        int length = Integer.toString(postal).length();
        if(length!=5){
            return "-1";
        }else{
            return num;
        }
    }
}
