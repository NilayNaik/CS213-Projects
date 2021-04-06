{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf840
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red127\green0\blue85;\red100\green100\blue100;\red106\green62\blue62;
}
{\*\expandedcolortbl;;\csgenericrgb\c49804\c0\c33333;\csgenericrgb\c39216\c39216\c39216;\csgenericrgb\c41569\c24314\c24314;
}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs22 \cf2 import\cf0  \cf2 static\cf0  org.junit.Assert.*;\
\
\cf2 import\cf0  org.junit.Test;\
\
\cf2 public\cf0  \cf2 class\cf0  DateTest \{\
	\cf3 @Test\cf0 \
	\cf2 public\cf0  \cf2 void\cf0  testIsValid () \{\
		Date \cf4 date1\cf0  = \cf2 new\cf0  Date (2020, 2, 29);\
		Date \cf4 date2\cf0  = \cf2 new\cf0  Date (2019, 2, 29);\
		Date \cf4 date3\cf0  = \cf2 new\cf0  Date (0, 3, 15);\
		Date \cf4 date4\cf0  = \cf2 new\cf0  Date (2019, 0, 29);\
		Date \cf4 date5\cf0  = \cf2 new\cf0  Date (2019, 13, 29);\
		Date \cf4 date6\cf0  = \cf2 new\cf0  Date (2019, 4, 31);\
		Date \cf4 date7\cf0  = \cf2 new\cf0  Date (2019, 3, 31);\
		assertTrue (\cf4 date1\cf0 .isValid());\
		assertFalse (\cf4 date2\cf0 .isValid());\
		assertFalse (\cf4 date3\cf0 .isValid());\
		assertFalse (\cf4 date4\cf0 .isValid());\
		assertFalse (\cf4 date5\cf0 .isValid());\
		assertFalse (\cf4 date6\cf0 .isValid());\
		assertTrue (\cf4 date7\cf0 .isValid());\
	\}\
\}\
}