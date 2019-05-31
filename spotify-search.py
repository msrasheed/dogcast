import time
import mysql.connector
from mysql.connector import Error
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains

# Runs the whole program by calling the subfunctions.
def main():
    flag = 0
    while flag == 0:
        song_name = readIn()
        if song_name == 1:
            print("All songs have been queued!")
            flag = 1
        else:
            driver = webdriver.Chrome("C:/Users/teddy/Documents/DogCast/chromedriver.exe")
            driver.get("https://open.spotify.com/search/recent")
            Login(driver)
            SongSearch(driver, song_name)
            time.sleep(2)
            driver.close()
            delete(song_name)

# Reads in the string from SQL

def readIn():
    try: 
        mySQLconnection = mysql.connector.connect(host = 'localhost', database='dank_playlist', user='root',password='Taosers1!')
        sql_select_query = "SELECT NAME FROM songs_table ORDER BY id ASC LIMIT 1"
        cursor = mySQLconnection.cursor()
        cursor.execute(sql_select_query)
        result = cursor.fetchall()
        if len(result) < 1:
            time.sleep(3)
            return 1
        else:
            song_name = result
            print(song_name)
            return song_name
    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if(mySQLconnection .is_connected()):
            mySQLconnection.close()
            print("MySQL connection is closed")
#Login function logs in Teddy so that the songs are queued on his Spotify account.

def Login(driver):
    #Finds and clicks the login button
    login_element = driver.find_element_by_xpath("//button[@class='btn btn-black btn--no-margin btn--full-width P7Qjj40AVoE8Igi7Ji05m _1xNlj_ScH8hEMWzrkRt1A']")
    login_element.click()
    time.sleep(1)
    #Types in the password, then the username, and then submits the form
    psw_element = driver.find_element_by_id("login-password")
    psw_element.send_keys("Taosers1!")
    click_element = driver.find_element_by_id("login-username")
    click_element.click()
    username_element = driver.find_element_by_id("login-username")
    username_element.send_keys("teddygalanthay1",Keys.ENTER)
    
#This function takes in two arguments, the driver and the string from SQL. The function then searches the song and adds it to the queue.

def SongSearch(driver, song_name):
    song_name = str(song_name)
    time.sleep(1)
    #Finds the search box
    element = driver.find_element_by_xpath("//input[@class='SearchInputBox__input']")
    #Types in the SQL string into the search bar
    element.send_keys(song_name, Keys.ENTER)
    element.clear()
    time.sleep(1)
    #Finds and right clicks the first result from the search
    element = driver.find_element_by_xpath("//li[@tabindex='0']")
    action = ActionChains(driver)
    action.move_to_element(element).perform()
    action2 = ActionChains(driver)
    action2.context_click(element).perform()
    time.sleep(1)
   # Finds the "Add to Queue" button, and then adds the song to the queue
    action3 = ActionChains(driver)
    element = driver.find_element_by_xpath("//*[text() = 'Add to Queue']")
    action3.move_to_element(element).perform()
    time.sleep(1)
    element.click()
   # Lets the user know their song has been added to the queue
    print("Added",song_name," to the queue")

#Deletes the song from the database.
def delete(song_name):
    try:
        mySQLconnection = mysql.connector.connect(host = 'localhost', database='dank_playlist', user='root',password='Taosers1!')
        sql_check = "SELECT * FROM songs_table"
        cursor2 = mySQLconnection.cursor()
        cursor2.execute(sql_check)
        result = cursor2.fetchall()
        sql_query = "DELETE FROM songs_table ORDER BY id ASC LIMIT 1"
        cursor = mySQLconnection.cursor()
        cursor.execute(sql_query)
        mySQLconnection.commit()
        print(song_name," Removed from Database")
    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if(mySQLconnection .is_connected()):
            mySQLconnection.close()
            print("MySQL connection is closed")
main()
