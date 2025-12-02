Path URL	Description	Entity	HTTP Methods
/api/films/post	Add new Film object in DB	Film	POST
/api/films/update/category/{id}	Update Category of a Film	Film	PUT
/api/films/update/language/{id}	Update Language of a Film	Film	PUT
/api/films/update/rating/{id}	Update  Rating of a Film	Film	PUT
/api/films/update/rentalrate/{id}	Update Rental Rate of a Film	Film	PUT
/api/films/update/rentaldurtion/{id}	Update Rental Duration of a Film	Film	PUT
/api/films/update/releaseyear/{id}	Update Release Year of a Film	Film	PUT
/api/films/update/title/{id}	Update Title of a Film	Film	PUT
/api/films/{id}/actor	Assign  Actor to a Film	Film	PUT
/api/films/category/{category}	Find all Films of specified   {category}	Film	GET
/api/films/{id}/actors	Find all Actors of a Film by Film id	Film	GET
/api/films/countbyyear	Display number of Films released   by each Year	Film	GET
/api/films/language/{lang}	Search Films where Language is {lang}	Film	GET
/api/films/rating/gt/{rating}	Search Films where Rating is   greater than {rating}	Film	  GET
/api/films/rating/lt/{rating}	Search Films where Rating is lower than {rating}	Film	GET
/api/films/betweenyear/{from}/{to}	Search  Films which are Released between {from} and {to}	Film	GET
/api/films/length/lt/{length}	Search Films where Length is  lower than {length}	Film	GET
/api/films/rate/lt/{rate}	Search Films where Rental Rate  is lower than {rate}	Film	GET
/api/films/duration/lt/{rd}	Search Films where Rental Duration is lower than {rd}	Film	GET
/api/films/length/gt/{length}	Search Films where Length is greater than {length}	Film	GET
/api/films/rate/gt/{rate}	Search Films where Rental Rate is greater than {rate}	Film	GET
/api/films/duration/gt/{rd}	Search Films where Rental Duration is greater than {rd}	Film	GET
/api/films/year/{year}	Search Films by Release Year	Film	GET
/api/films/title/{title}	Search Films by Title	Film	GET
/api/actors/{id}/film	Assign Film to an Actor	Actors	PUT
/api/actors/{id}/films	Search Films of an Actor by Actor Id	Actors	GET
/api/actors/update/firstname/{id}	Update First Name of an Actor	Actors	PUT
/api/actors/update/lastname/{id}	Update Last Name of an Actor	Actors	PUT
/api/actors/post	Add new Actor object in DB	Actors	POST
/api/customers/post	Add new Customer Object in DB	Customer	POST
/api/customers/update/{id}/store	Assign Store to a Customer	Customer	PUT
/api/customers/update/{id}/email	Update email of Customer	Customer	PUT
/api/customers/update/{id}/ln	Update last name of Customer	Customer	PUT
/api/customers/update/{id}/{fn}	Update first name of Customer.	Customer	PUT
/api/customers/phone/{phone}	Search Customers by phone number	Customer	GET
/api/customers/inactive	Search all inactive Customers	Customer	GET
/api/customers/active	Search all active Customers	Customer	GET
/api/customers/country/{country}	Search Customers by Country	Customer	GET
/api/customers/city/{city)	Search Customers by City	Customer	GET
/api/customers/{id}/{addressId}	Assign Address to a Customer	Customer	PUT
/api/customers/email/{email}	Search Customers by email	Customer	GET
/api/customers/firstname/{fn}	Search Customers by first name	Customer	GET
/api/customers/lastname/{ln}	Search Customers by last name	Customer	GET
/api/customers/update/{id}/phone	Update phone number of a Customer	Customer	PUT
/api/staff/post	Add new Staff object to DB	Staff	POST
/api/staff/update/phone/{id}	Update phone number of a Staff	Staff	PUT
/api/staff/update/store/{id}	Assign Store to a Staff	Staff	PUT
/api/staff/update/email/{id}	Update email of Staff	Staff	PUT
/api/staff/update/ln/{id}	Update last name of Staff	Staff	PUT
/api/staff/update/fn/{id}	Update first name of Staff	Staff	PUT
/api/staff/phone/{phone}	Search Staff by phone number	Staff	GET
/api/staff/country/{country}	Search Customers by Country	Staff	GET
/api/staff/city/{city}	Search Staff by City	Staff	GET
/api/Staff/{id}/address	Assign Address to a Staff	Staff	PUT
/api/staff/email/{email}	Search Staff by email	Staff	GET
/api/staff/firstname/{fn}	Search Staff by first name	Staff	GET
/api/staff/lastname/{ln}	Search Staff by last name	Staff	GET
/api/actors/firstname/{fn}	Search Actors by First Name	Actors	GET
/api/actors/lastname/{ln}	Search Actors by Last Name	Actors	GET
/api/actors/toptenbyfilmcount	Find top 10 Actors by Film Count	Actors	GET
/api/store/manager/{storeId}	Display Manager Details of a Store	Store	GET
/api/store/customer/{storeId}	Display all Customers of a Store	Store	GET
/api/store/staff/{storeId}	Display all Staff of a Store	Store	GET
/api/store/{storeId}/manager/{manager_staff_id}	Assign manager to a Store	Store	PUT
/api/store/update/{storeId}/{phone}	Update phone number of a Store	Store	PUT
/api/store/phone/{phone}	Search Store by phone number	Store	GET
/api/store/country/{country}	Search Store by Country	Store	GET
/api/store/city/{city}	Search Store by City	Store	GET
/api/store/{storeId}/address/{addressId}	Assign Address to a Store	Store	PUT
/api/store/post	Add new Store object to DB	Store	POST
/api/store/managers 	Display Manager details (first name, last name, email,  phone) and Store details (Address, City, Phone) of all stores	Store	GET
/api/inventory/add	Add one more Film to a Store	Inventory	POST
/api/inventory/film/{id}	Display inventory(count) of a Film in all Stores	Inventory	GET
/api/inventory/store/{id}	Display inventory of all Films by a Store	Inventory	GET
/api/inventory/films	Display inventory(count) of all Films in all Stores	Inventory	GET
/api/inventory/film/{id}/store/{id}	Display inventory(count) of a Film in a Store	Inventory	GET
/api/rental/add	Rent a  Film	Rental	POST
/api/rental/due/store/{id}	Display list of Customers who have not yet returned the  Film	Rental	GET
/api/rental/toptenfilms/store/{id}	Display top 10 most rented Films of a Store	Rental	GET
/api/rental/toptenfilms	Display top 10 most rented Films	Rental	GET
/api/rental/customer/{id}	Display all Films rented to a customer	Rental	GET
/api/rental/update/returndate/{id}	Update return date	Rental	POST
/api/payment/add	Make one Payment entry	payment	PUT
/api/payment/revenue/films/store/{id}	Calculate Cumulative revenue of all Films by Store	payment	GET
/api/payment/revenue/film/{id}	Calculate Cumulative revenue of a Film Store wise	payment	GET
/api/payment/revenue/filmwise/	Calculate Cumulative revenue of all Films (across all  stores)	payment	GET
/api/payment/revenue/datewise/store/{id}	Calculate Cumulative revenue of a Store	payment	GET
/api/payment/revenue/datewise	Calculate Cumulative revenue of all Stores	payment	GET
<img width="1087" height="7193" alt="image" src="https://github.com/user-attachments/assets/fc91d748-3073-4771-8d9e-c823a0d5d5a8" />
