# Slow post

This application was written just for educational purposes only, It represents a implementation
of slow post http attack. For demo was used a ubuntu server running on a Virtual Machine which 
characteristics arel listed bellow.

### Victim

Simple website with just a html file hosted on ubuntu server using apache.

* Virtual Machine
* Ubuntu Server x64
* 1024 MB RAM
* 1 Core
* html file and apache

### Attacker

A java application that opens multiple connection and which sends through that connections 
http headers with random content length and witch then slowly with a interval of 300 ms.

##### Usage

```bash
java -jar slowpost.jar <host> <path> <threads>
```

### Demo

![htop on last test](/screenshots/htop.png)
