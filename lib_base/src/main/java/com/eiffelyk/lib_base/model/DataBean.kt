package com.eiffelyk.lib_base.model

data class DataBean(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Any>,
    val title: String="",
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataBean

        if (niceDate != other.niceDate) return false
        if (title != other.title) return false
        if (desc != other.desc) return false
        if (author != other.author) return false
        if (shareUser != other.shareUser) return false
        if (id != other.id) return false
        if (chapterName != other.chapterName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = niceDate.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + shareUser.hashCode()
        result = 31 * result + id
        result = 31 * result + chapterName.hashCode()
        return result
    }
}