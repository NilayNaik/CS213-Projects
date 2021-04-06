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
\
\cf2 public\cf0  \cf2 class\cf0  CheckingTest \{\
	\
	\cf3 @Test\cf0 \
	\cf2 public\cf0  \cf2 void\cf0  testMonthlyInterest () \{\
		Checking \cf4 acct\cf0  = \cf2 new\cf0  Checking (\cf2 new\cf0  Profile (\cf5 "John"\cf0 , \cf5 "Doe"\cf0 ), 10000, \cf2 new\cf0  Date (1999, 7, 2), \cf2 true\cf0 );\
		assertEquals (5.0 / 12.0, \cf4 acct\cf0 .monthlyInterest(), 0.01);\
	\}\
	\
	\cf2 public\cf0  \cf2 void\cf0  testMonthlyFee () \{\
		Checking \cf4 acct1\cf0  = \cf2 new\cf0  Checking (\cf2 new\cf0  Profile (\cf5 "John"\cf0 , \cf5 "Doe"\cf0 ), 1000, \cf2 new\cf0  Date (1999, 7, 2), \cf2 true\cf0 );\
		Checking \cf4 acct2\cf0  = \cf2 new\cf0  Checking (\cf2 new\cf0  Profile (\cf5 "Jane"\cf0 , \cf5 "Doe"\cf0 ), 1000, \cf2 new\cf0  Date (1999, 7, 2), \cf2 false\cf0 );\
		Checking \cf4 acct3\cf0  = \cf2 new\cf0  Checking (\cf2 new\cf0  Profile (\cf5 "Jonathan"\cf0 , \cf5 "Doe"\cf0 ), 2000, \cf2 new\cf0  Date (1999, 7, 2), \cf2 false\cf0 );\
		assertEquals (0.0, \cf4 acct1\cf0 .monthlyFee(), 0.01);\
		assertEquals (25.5, \cf4 acct2\cf0 .monthlyFee(), 0.01);\
		assertEquals (0.0, \cf4 acct3\cf0 .monthlyFee(), 0.01);\
	\}\
\}}