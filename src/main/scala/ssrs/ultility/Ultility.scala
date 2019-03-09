package ssrs.ultility

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

import com.typesafe.config.Config
import com.vdurmont.emoji.EmojiParser

import scala.collection.JavaConversions._

object Ultility extends Serializable {
  def getStringList(conf: Config, key: String): List[String] = {
    try {
      conf.getStringList(key).toList
    } catch {
      case e: Exception => null
    }
  }

  def getCurrentDateUTC(): String = {
    val currentTime = System.currentTimeMillis
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    format.format(new Date(currentTime - TimeUnit.HOURS.toMillis(7)))
  }

  def getCurrentDate(): String = {
    val currentTime = System.currentTimeMillis
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    format.format(new Date(currentTime))
  }

  def convertFullName(fullName: String): String = {
    val str = EmojiParser.removeAllEmojis(fullName)
    stringLeft(str, 100)
  }

  def removeEmojis(str: String): String ={
    if (str == null) "" else EmojiParser.removeAllEmojis(str)
  }

  def stringLeft(str: String, index: Int): String = {
    if (str == null) "" else if (str.length > index) str.substring(0, index).trim else str.trim
  }

  def convertGender(gender: String): Int = {
    if (gender == null) 0 else if (gender.equals("1")) 1 else if (gender.equals("2")) 2 else 0
  }

  def checkIsNull(str: String): String = {
    if (str == null) "0" else str
  }

  def checkIsKXD(str: String): String = {
    if (str == null) "KXD" else str
  }

  def converDateTime(date: Date): String = {
    val format = new SimpleDateFormat("yyyyMMddHHmm")
    if(date== null) "000000000000" else format.format(date)
  }

  def stringTrim(str: String): String = {
    if (str == null) "" else str.trim
  }

  def convertNameOfCod(nameOfCod: String): String = {
    if(nameOfCod == null || nameOfCod.equals("")) "KXD"
    else if (nameOfCod.equals("Ong Vàng") || nameOfCod.equals("Ong vàng")) "GHOV"
    else if (nameOfCod.equals("Viettel")) "GHVT"
    else if (nameOfCod.equals("COD GHTK SN")) "GHTK"
    else if (nameOfCod.equals("COD VTHN")) "VTHN"
    else if (nameOfCod.equals("ninjavan")) "Ninjavan"
    else nameOfCod
  }

  def converBank(bank: String): String = {
    if(bank == null || bank.equals("")) "KXD"
    else if (bank.equals("1") || bank.equals("2")) "VIETCOMBANK"
    else if (bank.equals("5") || bank.equals("3") || bank.equals("6")) "VIETINBANK"
    else if (bank.equals("4")) "SACOMBANK"
    else bank.toString
  }

}
