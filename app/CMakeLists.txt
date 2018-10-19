# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
project(libgzstream LANGUAGES C)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
project(ansvif CXX )
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Werror -std=gnu++11 -D__ANDROID__ -D__linux -fPIE -pie")
set( srcdir ../ansvif/src/)
set( incdir ../ansvif/include/)
set( ansvifsources
${srcdir}log.cpp
${srcdir}main.cpp
${srcdir}bin2hex.cpp
${srcdir}popen2.cpp
${srcdir}main.cpp
${srcdir}help.cpp
${srcdir}match_fault.cpp
${srcdir}sys_string.cpp
${srcdir}man_read.cpp
${srcdir}trash.cpp
${srcdir}randomizer.cpp
${srcdir}remove_chars.cpp
${srcdir}to_int.cpp
${srcdir}file_check.cpp
${srcdir}reaper.cpp
${srcdir}write_file.cpp
${srcdir}templates.cpp
${incdir}gzstream/gzstream.h
${incdir}xmlwriter/xml_writer.hpp
${srcdir}version.h )
set(gzstreamsources ${incdir}gzstream/gzstream.h ${incdir}gzstream/gzstream.cpp)

add_library( gzstream ${gzstreamsources} )
target_compile_options( gzstream PUBLIC -std=c++11 -fpermissive -w -Wall )
add_executable(ansvif ${ansvifsources} )

target_compile_options(ansvif PUBLIC -std=c++11 -fpermissive -w -Wall )




target_link_libraries(ansvif -lz -lstdc++ -pthread )
