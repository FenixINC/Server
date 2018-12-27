package com.server.server.controller

import com.server.server.entity.Login
import com.server.server.entity.User
import com.server.server.service.UserService
import com.server.server.utils.HashMD5
import com.server.server.utils.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException

@RestController
@RequestMapping("/login")
class AppUserController {

    private val LOG = LoggerUtils(AppUserController::class.java)

    private var mResult = "false"
    private var mResponse: ResponseEntity<*>? = null

    @Autowired
    private val mService: UserService? = null

    @PostMapping("/create")
    fun appUserCreate(@RequestBody login: Login): ResponseEntity<*>? {
        val username = login.username
        val password = login.password
        var salt: ByteArray? = null
        val newUser = User()

        if (!isUserExist(username, password, true)) {
            try {
                salt = HashMD5.getSalt()
                newUser.username = username
                newUser.userSalt = salt.toString()
                newUser.passwordHash = HashMD5.getSecurePassword(password, salt)
                mService?.createUser(newUser)
                LOG.info(login.toString())
                LOG.info("Create new user: " + newUser.toString())
            } catch (e: NoSuchAlgorithmException) {
                LOG.error(e.message)
            } catch (e: NoSuchProviderException) {
                LOG.error(e.message)
            } finally {
                LOG.info("--------------")
            }
        }

        return if (mResponse != null) mResponse else ResponseEntity.status(HttpStatus.OK).body(mResult)
    }

    @PostMapping("/login")
    @ResponseBody
    fun userLogin(@RequestBody login: Login): ResponseEntity<*> {
        isUserExist(login.username, login.password, false)
        LOG.info("--------------")
        return if (mResponse != null) mResponse!! else ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
    }

    @PostMapping("/reset")
    @ResponseBody
    fun userResetPassword(@RequestBody login: Login): ResponseEntity<*> {
        resetPassword(login.username, login.password)
        LOG.info("--------------")
        return if (mResponse != null) mResponse!! else ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
    }

    private fun isUserExist(username: String, password: String, isCreateNewUser: Boolean): Boolean {
        val user = mService?.findUserByName(username)
        if (user != null) {
            when {
                isCreateNewUser -> {
                    LOG.info("Cannot create user. This user $username is already exists!")
                    mResult = "Cannot create user. This user <b>$username</b> is already exists!"
                    mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
                    return true
                }
                HashMD5.getSecurePassword(password, null).equals(user.passwordHash, ignoreCase = true) -> {
                    LOG.info("Success response login. " + user.toString())
                    mResult = "Success response login."
                    mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult)
                    return true
                }
                else -> {
                    LOG.info("Wrong password!")
                    mResult = "Wrong password!"
                    mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
                    return true
                }
            }
        } else if (user == null && isCreateNewUser) {
            LOG.info("Successful response creating new user.")
            mResult = "Successful response creating new user."
            mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult)
            return false
        } else {
            LOG.info("User absent and == NULL")
            mResult = "User absent and == NULL"
            mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
            return false
        }
    }

    private fun resetPassword(username: String, password: String) {
        val user = mService?.findUserByName(username)
        if (user != null) {
            user.passwordHash = HashMD5.getSecurePassword(password, user.userSalt?.toByteArray())
            mService?.updateUserPasswordHash(user.id!!, user.passwordHash)
            mResult = "Successful creating new password."
            mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult)
            LOG.info("Created new password: " + password + ", passwordHash: " + user.passwordHash + ", for user: " + user.username)
        } else {
            LOG.info("User absent and == NULL")
            mResult = "User absent and == NULL"
            mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult)
        }
    }
}