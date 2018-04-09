#!/bin/sh
curl -T "{test.txt}" ftp://localhost:2121/camera1/test.txt --user upload:uploadp1x
