{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf840
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red100\green100\blue100;\red106\green62\blue62;
\red42\green0\blue255;}
{\*\expandedcolortbl;;\csgenericrgb\c49804\c0\c33333;\csgenericrgb\c39216\c39216\c39216;\csgenericrgb\c41569\c24314\c24314;
\csgenericrgb\c16471\c0\c100000;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs22 \cf2 import\cf0  \cf2 static\cf0  org.junit.Assert.assertEquals;\
\
\cf2 import\cf0  org.junit.Test;\
\cf2 public\cf0  \cf2 class\cf0  MoneyMarketTest \{\
	\cf3 @Test\cf0 \
	\cf2 public\cf0  \cf2 void\cf0  testMonthlyInterest () \{\
		MoneyMarket \cf4 acct\cf0  = \cf2 new\cf0  MoneyMarket (\cf2 new\cf0  Profile (\cf5 "Hello"\cf0 , \cf5 "Goodbye"\cf0 ), 10000, \cf2 new\cf0  Date (1999, 7, 2));\
		assertEquals (65.0 / 12.0, \cf4 acct\cf0 .monthlyInterest(), 0.01);\
	\}\
	\
	\cf3 @Test\cf0 \
	\cf2 public\cf0  \cf2 void\cf0  testMonthlyFees () \{\
		MoneyMarket \cf4 acct1\cf0  = \cf2 new\cf0  MoneyMarket (\cf2 new\cf0  Profile (\cf5 "Hello"\cf0 , \cf5 "Goodbye"\cf0 ), 10000, \cf2 new\cf0  Date (1999, 7, 2));\
		MoneyMarket \cf4 acct2\cf0  = \cf2 new\cf0  MoneyMarket (\cf2 new\cf0  Profile (\cf5 "Hola"\cf0 , \cf5 "Buenos Dias"\cf0 ), 1000, \cf2 new\cf0  Date (1999, 7, 2));\
		assertEquals (0.0, \cf4 acct1\cf0 .monthlyFee(), 0.01);\
		assertEquals (12.0, \cf4 acct2\cf0 .monthlyFee(), 0.01);\
		\cf2 for\cf0  (\cf2 int\cf0  \cf4 i\cf0  = 0; \cf4 i\cf0  < 7; \cf4 i\cf0 ++) \cf4 acct1\cf0 .debit(100);\
		assertEquals (12.0, \cf4 acct1\cf0 .monthlyFee(), 0.01);\
	\}\
\}\
}