/**
 * Find Security Bugs
 * Copyright (c) 2013, Philippe Arteau, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.h3xstream.findsecbugs.endpoint;

import com.h3xstream.findbugs.test.BaseDetectorTest;
import com.h3xstream.findbugs.test.EasyBugReporter;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ServletEndpointDetectorTest extends BaseDetectorTest {

    @Test
    public void detectServletVariousInputs() throws Exception {
        //Locate test code
        String[] files = {
                getClassFilePath("testcode/BasicServlet")
        };

        //Run the analysis
        EasyBugReporter reporter = spy(new EasyBugReporter());
        analyze(files, reporter);


        //Assertions

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_CONTENT_TYPE")
                        .inClass("BasicServlet").inMethod("doGet").atLine(16)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_SERVER_NAME")
                        .inClass("BasicServlet").inMethod("doGet").atLine(17)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_SESSION_ID")
                        .inClass("BasicServlet").inMethod("doGet").atLine(19)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_QUERY_STRING")
                        .inClass("BasicServlet").inMethod("doGet").atLine(20)
                        .build()
        );

        //Headers

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_HEADER_REFERER")
                        .inClass("BasicServlet").inMethod("doGet").atLine(22)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_SERVER_NAME")
                        .inClass("BasicServlet").inMethod("doGet").atLine(24)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_HEADER_USER_AGENT")
                        .inClass("BasicServlet").inMethod("doGet").atLine(25)
                        .build()
        );

        verify(reporter).doReportBug(
                bugDefinition()
                        .bugType("SERVLET_HEADER")
                        .inClass("BasicServlet").inMethod("doGet").atLine(26)
                        .build()
        );

        for (Integer line : Arrays.asList(35, 37, 39, 41)) {
            verify(reporter).doReportBug(
                    bugDefinition()
                            .bugType("SERVLET_PARAMETER")
                            .inClass("BasicServlet").inMethod("useParameters").atLine(line)
                            .build()
            );
        }
    }

}
