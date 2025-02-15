/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.UnsupportedCommandException;

import static io.appium.java_client.MobileCommand.hideKeyboardCommand;

public interface HidesKeyboardWithKeyName extends HidesKeyboard {

    /**
     * Hides the keyboard by pressing the button specified by keyName if it is
     * showing. If the on-screen keyboard does not have any dedicated button that
     * hides it then an error is going to be thrown. In such case you must emulate
     * same actions an app user would do to hide the keyboard.
     * See the documentation for 'mobile: hideKeyboard' extension for more details.
     *
     * @param keyName The button pressed by the mobile driver to attempt hiding the
     *                keyboard.
     */
    default void hideKeyboard(String keyName) {
        final String extName = "mobile: hideKeyboard";
        try {
            CommandExecutionHelper.executeScript(assertExtensionExists(extName), extName, ImmutableMap.of(
                "keys", ImmutableList.of(keyName)
            ));
        } catch (UnsupportedCommandException e) {
            // TODO: Remove the fallback
            CommandExecutionHelper.execute(markExtensionAbsence(extName), hideKeyboardCommand(keyName));
        }
    }

    /**
     * Hides the keyboard if it is showing. Hiding the keyboard often
     * depends on the way an app is implemented, no single strategy always
     * works.
     *
     * @param strategy HideKeyboardStrategy.
     * @param keyName  a String, representing the text displayed on the button of the
     *                 keyboard you want to press. For example: "Done".
     * @deprecated This API is deprecated and will be removed in the future.
     */
    @Deprecated
    default void hideKeyboard(String strategy, String keyName) {
        CommandExecutionHelper.execute(this, hideKeyboardCommand(strategy, keyName));
    }
}
