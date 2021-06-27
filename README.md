# LEJLEKU Android App

Lejleku is an e-Commerce android app implemented using Java in AndroidStudio and Firebase database.



# **Functionality**


Lejleku allows user to order tech products online using android. In this app are integrated all functionalities of a basic e-commerce app.

The app is divided into three parts. Verification sector, admin sector and user sector.

## Access and verification

The main part is where the user should be registered and then verified, he will get access to our page depending on whether he is an admin or a simple user.

![enter image description here](https://images4.imagebam.com/2a/5f/a6/ME1E0NW_o.jpg)

## Admin Sector
If you have logged in and your data proves that you are an admin, then you will have access to the admin sector. 

In Admin Sector you can select product categories and add a new product through the application in the database, which will then be automatically displayed to the user

## User Sector
The most important part of this application is the user sector. If your data proves that you are an user, then you will have access to the user sector.

Once you have logged in you will be shown Home Activity which contains the most popular products, product categories and recommended products. On the left you also have the Navigation Drawer that shows our app's main navigation menu.

![enter image description here](https://images4.imagebam.com/fe/92/77/ME1E0NO_o.jpg)

If you click on one of the product categories you will be shown the products for those categories. You can click each product to get additional information and add it to your cart according to the quantity you want.

In the Navigation drawer we also have the MyCart menu where you can see the products you have added to the cart and their total price. You can also remove products from the cart, but you can also order them. You can also buy products, but to order them you need to add your address, after adding your address you can proceed to payment method implemented with RazorPay.

We also have InfoFragment where you can get additional information from us, including the location in GoogleMap implemented through Maps SDK for Android.


# Implementation

Implemented with: 

- Java
- Android Studio IDE
-  Android SDK
- Razorpay SDK for Android
- Maps SDK for Android
-  Firebase Authentication
-  Firebase Database 
-  Firebase Storage

